package com.example.studentManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
    private String name;

    private String location;

    @Column(name = "type_name")
    private String type;

}
