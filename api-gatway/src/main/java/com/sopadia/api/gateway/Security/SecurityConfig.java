package com.sopadia.api.gateway.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
   @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
   String jwkSetUri;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .csrf(csrfSpec -> csrfSpec.disable())
                .authorizeExchange(exchange -> exchange
                        .pathMatchers(HttpMethod.GET).permitAll()
                        .pathMatchers("/eureka/**").permitAll()
                        .pathMatchers("/api/products/**").authenticated()
                        .anyExchange().authenticated())
                //.oauth2ResourceServer(oauth-> oauth.jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(jwtConverter))).build();
                .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults())).build();
    }

   @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
   }
}
