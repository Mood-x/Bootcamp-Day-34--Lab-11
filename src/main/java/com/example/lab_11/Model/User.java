package com.example.lab_11.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @NotEmpty(message = "Username should be not empty")
    @Size(min = 4, message = "Username must be more than 3 character")
    @Column(columnDefinition = "varchar(15) not null unique")
    private String username; 


    @NotEmpty(message = "Password should be not empty")
    @Size(min = 6, message = "Password must be more than 6 character")
    @Column(columnDefinition = "varchar(25) not null")
    private String password; 

    @NotEmpty(message = "Email should be not empty")
    @Email
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email; 

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "date not null")
    private LocalDate registrationDate;

    private List<Integer> followers = new ArrayList<>(); 
    private List<Integer> following = new ArrayList<>(); 



}
