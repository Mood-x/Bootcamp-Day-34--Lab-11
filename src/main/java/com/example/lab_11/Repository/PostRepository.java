package com.example.lab_11.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lab_11.Model.Post;

@Repository
public interface  PostRepository extends JpaRepository<Post, Integer>{

    Post findPostById(Integer id);
    List<Post> findAllPostByUserId(Integer id);
    Post findPostByTitle(String postTitle);
    // List<Post> findPostByCategory(String categoryName);
    @Query("SELECT p FROM Post p WHERE p.publishDate < :date")
    List<Post> findAllPostsBeforePublishDate(@Param("date") LocalDate date);
}
