//package com.example.studentManagement.config;
//
//import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
//import org.keycloak.OAuth2Constants;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.keycloak.representations.idm.CredentialRepresentation;
//
//import static org.keycloak.models.PasswordPolicy.build;
//
//public class KeycloakConfig {
//
//    static Keycloak keycloak = null;
//    final static String serverUrl = "http://0.0.0.0:8081/realms/Innogent/protocol/openid-connect/token";
//    public final static String realm = "Innogent";
//    final static String clientId = "Grapphiler";
//    final static String clientSecret = "pfkPRydvSZstJMRpB1OoZ6URDOxXTITj";
//    final static String userName = "admin";
//    final static String password = "admin";
//
//    public KeycloakConfig() {
//    }
//
//    public static Keycloak getInstance(){
//        if(keycloak == null){
//
//            keycloak = KeycloakBuilder.builder()
//                    .serverUrl(serverUrl)
//                    .realm(realm)
//                    .grantType(OAuth2Constants.PASSWORD)
//                    .username("suresh")
//                    .password("demo@123")
//                    .clientId(clientId)
//                    .clientSecret(clientSecret)
//                    .resteasyClient(new ResteasyClientBuilder()
//                            .connectionPoolSize(10)
//                            .build())
//                    .build();
//        }
//        return keycloak;
//    }
//
//    public static CredentialRepresentation createPasswordCredentials(String password) {
//        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
//        passwordCredentials.setTemporary(false);
//        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
//        passwordCredentials.setValue(password);
//        return passwordCredentials;
//    }
//}
