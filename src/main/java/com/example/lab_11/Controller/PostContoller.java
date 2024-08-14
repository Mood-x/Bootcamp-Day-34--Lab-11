package com.example.lab_11.Controller;

import java.time.LocalDate;
import java.util.List;

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
import com.example.lab_11.Model.Post;
import com.example.lab_11.Service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostContoller {

    private final PostService postService; 

    @GetMapping
    public ResponseEntity getPosts(){
        return ResponseEntity.status(200).body(postService.getPosts()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity getPostById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(postService.getPostById(id)); 
    }


    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("Post added successfully")); 
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @Valid @RequestBody Post post, Errors err){
        if(err.hasErrors()){
            String message = err.getFieldError().getDefaultMessage(); 
            return ResponseEntity.status(400).body(message); 
        }

        postService.updatePost(id, post);
        return ResponseEntity.status(200).body(new ApiResponse("Post updated successfully")); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post deleted successfully")); 
    }

    @GetMapping("/allPosts/{id}")
    public ResponseEntity getPostsByUserId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(postService.getPostsByUserId(id)); 
    }

    @GetMapping("/title/{postTitle}")
    public ResponseEntity getPostByTitle(@PathVariable String postTitle){
        return ResponseEntity.status(200).body(postService.getPostByTitle(postTitle)); 
    }

    @GetMapping("/date/{publishDate}")
    public ResponseEntity<List<Post>> getPostsBeforePublishedDate(@PathVariable String publishDate){
        LocalDate localDate = LocalDate.parse(publishDate); 
        List<Post> posts = postService.getPostsBeforePublishedDate(localDate); 
        return ResponseEntity.status(200).body(posts); 
    }

    @PostMapping("/like/{userId}/{postId}")
    public ResponseEntity likePost(@PathVariable Integer userId, @PathVariable Integer postId){
        postService.likePost(userId, postId);
        return ResponseEntity.status(200).body(new ApiResponse("Like")); 
    }

    @PostMapping("/dislike/{userId}/{postId}")
    public ResponseEntity disLikePost(@PathVariable Integer userId, @PathVariable Integer postId){
        postService.disLikePost(userId, postId);
        return ResponseEntity.status(200).body(new ApiResponse("Dislike")); 
    }
}
