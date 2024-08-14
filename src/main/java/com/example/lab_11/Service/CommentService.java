package com.example.lab_11.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lab_11.API.ApiExeption;
import com.example.lab_11.Model.Comment;
import com.example.lab_11.Model.Post;
import com.example.lab_11.Model.User;
import com.example.lab_11.Repository.CommentRepository;
import com.example.lab_11.Repository.PostRepository;
import com.example.lab_11.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository; 
    private final UserRepository userRepository; 
    private final PostRepository postRepository; 

    public List<Comment> getCommets(){
        if(commentRepository.findAll().isEmpty()){
            throw new ApiExeption("Not found comments"); 
        }
        return commentRepository.findAll(); 
    }

    public Comment getCommentById(Integer id){
        Comment comment = commentRepository.findCommentById(id); 

        if(comment == null){
            throw new ApiExeption("Comment with this ID: " + id + ", Not found"); 
        }
        return comment; 
    }


    public void addComment(Comment comment){
        User user = userRepository.findUserById(comment.getUserId()); 
        Post post = postRepository.findPostById(comment.getPostId()); 


        if(user == null || post == null){
            throw new ApiExeption(
                user == null 
                ? "User with this ID: " + comment.getUserId() + ", Not found"
                : "Post with this ID: " + comment.getPostId() + ", Not found"); 
        }
        commentRepository.save(comment); 
    }

    public void updateComment(Integer id, Comment updateComment){
        Comment comment = commentRepository.findCommentById(id); 

        if(comment == null){
            throw new ApiExeption("Comment with this ID: " + id + ", Not found"); 
        }

        comment.setContent(updateComment.getContent());
        commentRepository.save(comment); 
    }


    public void deletePost(Integer id){
        Comment comment = commentRepository.findCommentById(id); 

        if(comment == null){
            throw new ApiExeption("Comment with this ID: " + id + ", Not found"); 
        }
        commentRepository.delete(comment); 
    }

    public List<Comment> getCommentsByPostId(Integer id){
        Post post = postRepository.findPostById(id); 

        if(post == null){
            throw new ApiExeption("Post with this ID: "+ id + ", Not found"); 
        }

        List<Comment> comments = commentRepository.findAllCommentByPostId(id); 

        if(comments.isEmpty()){
            throw new ApiExeption("Not found any comments for this post: "); 
        }

        return comments; 
    }

}
