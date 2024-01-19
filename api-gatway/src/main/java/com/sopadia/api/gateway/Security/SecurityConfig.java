package com.sopadia.api.gateway.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtConverter jwtConverter;

    public SecurityConfig(JwtConverter jwtConverter) {
        this.jwtConverter = jwtConverter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
         return httpSecurity.csrf(csrfSpec -> csrfSpec.disable())

                 .authorizeExchange(exchange->exchange.pathMatchers("/eureka/**").permitAll()
                         .pathMatchers("/api/products/**").hasRole("dev")
                         .anyExchange().authenticated())
                 .oauth2ResourceServer(oauth-> oauth.jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(jwtConverter))).build();
    }
}
