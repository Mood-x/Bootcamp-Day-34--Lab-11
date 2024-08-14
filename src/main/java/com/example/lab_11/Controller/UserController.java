package com.example.lab_11.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab_11.API.ApiResponse;
import com.example.lab_11.Model.User;
import com.example.lab_11.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService; 

    @GetMapping
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(userService.getUserById(id)); 
    }


    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully")); 
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        userService.updateUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully")); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully")); 
    }


    @PostMapping("/follow/{followerId}/{followingId}")
    public ResponseEntity followUser(@PathVariable Integer followerId, @PathVariable Integer followingId){
        userService.followUser(followerId, followingId);
        return ResponseEntity.status(200).body(new ApiResponse("Follow")); 
    }

    @PostMapping("/unFollow/{followerId}/{followingId}")
    public ResponseEntity unFollowUser(@PathVariable Integer followerId, @PathVariable Integer followingId){
        userService.unFollowUser(followerId, followingId);
        return ResponseEntity.status(200).body(new ApiResponse("Unfollow")); 
    }
}
