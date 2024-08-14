package com.example.lab_11.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab_11.Model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

    Comment findCommentById(Integer id);
    List<Comment> findAllCommentByPostId(Integer id);
    

}
