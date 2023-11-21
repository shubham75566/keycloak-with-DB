package com.example.studentManagement.requestPayload;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserRequestPayload {

    private String firstName;
    private String lastName;
    private String password;
    private String email;

}
