package com.example.studentManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String emailId;
    private String userName;
    private String password;
    private String firstname;
    private String lastName;

}
