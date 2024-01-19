package com.sopadia.api.gateway.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
@Component
public class JwtConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>>{

    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {
        Map<String, Object> realmAccess = (Map<String, Object>)jwt.getClaims().get("realm_access");
        if(realmAccess==null || realmAccess.isEmpty()) return Mono.empty();
        Collection<GrantedAuthority>grantedAuthorities= ((List<String>)realmAccess.get("roles"))
                .stream().map(x-> "ROLE_" + x)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return Mono.just(grantedAuthorities)
                .map((authorities) -> new JwtAuthenticationToken(jwt, authorities));
    }
}
