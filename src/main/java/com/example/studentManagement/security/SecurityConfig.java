package com.example.studentManagement.security;

import java.io.IOException;

import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http.csrf(t -> t.disable());
//        System.out.println("ss");
        http.addFilterAfter(createPolicyEnforcerFilter(),
                BearerTokenAuthenticationFilter.class);

        http.sessionManagement(
                t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();
    }

    private ServletPolicyEnforcerFilter createPolicyEnforcerFilter() {
        return new ServletPolicyEnforcerFilter(

                new ConfigurationResolver() {
            @Override
            public PolicyEnforcerConfig resolve(HttpRequest request) {
                try {
                    return JsonSerialization.
                            readValue(getClass().getResourceAsStream("/policy-enforcer.json"),
                            PolicyEnforcerConfig.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }

}



































































//import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
//import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
//import org.keycloak.adapters.authorization.spi.HttpRequest;
//import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
//import org.keycloak.util.JsonSerialization;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.io.IOException;
//
//@Configuration
//@EnableWebSecurity
////@EnableMethodSecurity
//public class SecutiryConfig {
//
//    private final JwtAutConvertor jwtAutConvertor = new JwtAutConvertor();
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(c-> c.disable());
//        http
//                .authorizeHttpRequests(authozied-> authozied
//                        .anyRequest()
//                        .authenticated());
//
//        http
//                .oauth2ResourceServer(t-> t.jwt(congif->congif.jwtAuthenticationConverter(jwtAutConvertor)));
//
//        http
//                .sessionManagement(sm-> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();
//    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http)
////            throws Exception {
////        http.csrf(t -> t.disable());
////        http.addFilterAfter(createPolicyEnforcerFilter(),
////                BearerTokenAuthenticationFilter.class);
////
////        http.sessionManagement(
////                t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////        );
////        return http.build();
////    }
//
////    private ServletPolicyEnforcerFilter createPolicyEnforcerFilter() {
////        return new ServletPolicyEnforcerFilter(new ConfigurationResolver() {
////            @Override
////            public PolicyEnforcerConfig resolve(HttpRequest request) {
////                try {
////                    return JsonSerialization.
////                            readValue(getClass().getResourceAsStream("/policy-enforcer.json"),
////                            PolicyEnforcerConfig.class);
////                } catch (IOException e) {
////                    throw new RuntimeException(e);
////                }
////            }
////        });
////    }
//}
