//package com.example.studentManagement.service;
//
//import com.example.studentManagement.config.KeycloakConfig;
//import com.example.studentManagement.entity.User;
//import org.apache.http.auth.Credentials;
//import org.keycloak.admin.client.resource.UsersResource;
//import org.keycloak.representations.idm.CredentialRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//public class KeyCloakService {
//
//    public void addUser(User userRequest){
//        CredentialRepresentation credential = KeycloakConfig
//                .createPasswordCredentials(userRequest.getPassword());
//        UserRepresentation user = new UserRepresentation();
//        user.setUsername(userRequest.getUserName());
//        user.setFirstName(userRequest.getFirstname());
//        user.setLastName(userRequest.getLastName());
//        user.setEmail(userRequest.getEmailId());
//        user.setCredentials(Collections.singletonList(credential));
//        user.setEnabled(true);
//
//        UsersResource instance = getInstance();
//        instance.create(user);
//    }
//
//    public List<UserRepresentation> getUser(String userName){
//        UsersResource usersResource = getInstance();
//        List<UserRepresentation> user = usersResource.search(userName, true);
//        return user;
//
//    }
//
//    public void updateUser(String userId, User userRequest){
//        CredentialRepresentation credential = KeycloakConfig
//                .createPasswordCredentials(userRequest.getPassword());
//        UserRepresentation user = new UserRepresentation();
//        user.setUsername(userRequest.getUserName());
//        user.setFirstName(userRequest.getFirstname());
//        user.setLastName(userRequest.getLastName());
//        user.setEmail(userRequest.getEmailId());
//        user.setCredentials(Collections.singletonList(credential));
//
//        UsersResource usersResource = getInstance();
//        usersResource.get(userId).update(user);
//    }
//    public void deleteUser(String userId){
//        UsersResource usersResource = getInstance();
//        usersResource.get(userId)
//                .remove();
//    }
//
//
//    public void sendVerificationLink(String userId){
//        UsersResource usersResource = getInstance();
//        usersResource.get(userId)
//                .sendVerifyEmail();
//    }
//
//    public void sendResetPassword(String userId){
//        UsersResource usersResource = getInstance();
//
//        usersResource.get(userId)
//                .executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
//    }
//    public UsersResource getInstance(){
//        return KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users();
//    }
//
//}
