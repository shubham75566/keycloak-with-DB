package com.example.studentManagement.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@EnableMethodSecurity
public class JwtAutConvertor implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter= new JwtGrantedAuthoritiesConverter();

    private final String principleAttributes = "preferred_username";
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()
                )
                .collect(Collectors.toSet());

        return new JwtAuthenticationToken(jwt,authorities,getPrincipleClaimName(jwt));
    }
    private String getPrincipleClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (principleAttributes != null){
            claimName = principleAttributes;
        }
        return jwt.getClaim(claimName);
    }
    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {

        try {
            Map<String,Object> resourceAccess;
            Map<String,Object> resource;
            Collection<String> resourceRoles;
            if (jwt.getClaim("resource_access") == null){
                return Set.of();
            }
            resourceAccess = jwt.getClaim("resource_access");
            if (resourceAccess.get("Grapphiler") == null){
                return Set.of();
            }
            resource = (Map<String,Object>) resourceAccess.get("Grapphiler");
            resourceRoles = (Collection<String>) resource.get("roles");
            return resourceRoles
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                    .collect(Collectors.toSet() );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
