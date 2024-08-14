package com.example.lab_11.Model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer categoryId; 

    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotEmpty(message = "Post title should be not empty")
    @Size(min = 4, message = "Post title must be more than 3 characters")
    @Column(columnDefinition = "varchar(25) not null")
    private String title;

    @NotEmpty(message = "Post content should be not empty")
    @Size(min = 20, message = "Post content must be more than 19 characters")
    @Column(columnDefinition = "varchar(350) not null")
    private String content;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "date not null")
    private LocalDate publishDate;

    @Column(columnDefinition = "int default 0")
    private int likePost = 0; 

    @Column(columnDefinition = "int default 0")
    private int disLikePost = 0;

}
