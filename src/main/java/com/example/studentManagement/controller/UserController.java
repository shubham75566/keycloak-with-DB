package com.example.studentManagement.controller;

import com.example.studentManagement.requestPayload.UserRequestPayload;
import com.example.studentManagement.service.impl.KCService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@SecurityRequirement(name = "keycloak")
public class UserController {

    @Autowired
    KCService kcService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<UserRepresentation> createUser(@RequestBody UserRequestPayload user) {
        try {
            return new ResponseEntity<>(kcService.addUser(user),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
