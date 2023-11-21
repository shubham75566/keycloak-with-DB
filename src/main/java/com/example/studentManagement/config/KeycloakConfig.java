package com.example.studentManagement.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakConfig {

    Keycloak keycloak = null;
    @Value("${keycloak.serverUrl}")
    final  String serverUrl = "http://localhost:8081/";
    @Value("${keycloak.realm}")
    String realm;
    @Value("${keycloak.clientId}")
    String clientId;
    @Value("${keycloak.clientSecret}")
    String clientSecret;

    @Value("${keycloak.userName}")
    String userName;

    @Value("${keycloak.password}")
    String password;

    public KeycloakConfig() {
    }

    public Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build()).build();
        }
        return keycloak;
    }
}
