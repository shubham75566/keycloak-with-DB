package com.example.studentManagement;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableSwagger2
//@SecurityScheme(
//		name = "keycloak",
//		openIdConnectUrl = "http://0.0.0.0:8081/realms/WeManages/.well-known/openid-configuration",
//		scheme = "bearer",
//		type = SecuritySchemeType.OPENIDCONNECT,
//		in = SecuritySchemeIn.HEADER
//)
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

}
