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
import com.example.lab_11.Model.Comment;
import com.example.lab_11.Service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService; 


    @GetMapping
    public ResponseEntity getCommets(){
        return ResponseEntity.status(200).body(commentService.getCommets()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity getCommentById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(commentService.getCommentById(id)); 
    }


    @PostMapping("/add")
    public ResponseEntity addComment(@Valid @RequestBody Comment comment, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment added successfully")); 
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id, @Valid @RequestBody Comment comment, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        commentService.updateComment(id, comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment updated successfully")); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){
        commentService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment deleted successfully")); 
    }

    @GetMapping("/allComments/{postId}")
    public ResponseEntity getCommentsByPostId(@PathVariable Integer postId){
        return ResponseEntity.status(200).body(commentService.getCommentsByPostId(postId)); 
    }

}
