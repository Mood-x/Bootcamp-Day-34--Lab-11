package com.example.lab_11.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lab_11.API.ApiExeption;
import com.example.lab_11.Model.Category;
import com.example.lab_11.Model.Post;
import com.example.lab_11.Model.User;
import com.example.lab_11.Repository.CategoryRepository;
import com.example.lab_11.Repository.PostRepository;
import com.example.lab_11.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository; 
    private final CategoryRepository categoryRepository; 


    public List<Post> getPosts(){
        if(postRepository.findAll().isEmpty()){
            throw new ApiExeption("Not found posts"); 
        }
        return postRepository.findAll(); 
    }

    public Post getPostById(Integer id){
        Post post = postRepository.findPostById(id); 

        if(post == null){
            throw new ApiExeption("Post with this ID: " + id + ", Not found"); 
        }
        return post; 
    }


    public void addPost(Post post){
        User user = userRepository.findUserById(post.getUserId());
        Category category = categoryRepository.findCategoryById(post.getCategoryId()); 
        
        if(user == null || category == null){
            throw new ApiExeption(user == null 
            ? "User with this ID: " + post.getUserId() + ", Not found"
            : "Category with this ID: " + post.getCategoryId() + ", Not found"); 
        }

        postRepository.save(post); 
    }


    public void updatePost(Integer id, Post updatePost){
        Post post = postRepository.findPostById(id); 

        if(post == null){
            throw new ApiExeption("Post with this ID: " + id + ", Not found"); 
        }

        post.setTitle(updatePost.getTitle());
        post.setContent(updatePost.getContent());
        postRepository.save(post); 
    }


    public void deletePost(Integer id){
        Post post = postRepository.findPostById(id); 

        if(post == null){
            throw new ApiExeption("Post with this ID: " + id + ", Not found"); 
        }
        postRepository.delete(post); 
    }


    public List<Post> getPostsByUserId(Integer id){
        User user = userRepository.findUserById(id);

        if(user == null){
            throw new ApiExeption("User with this ID: " + id + ", Not found"); 
        }

        List<Post> posts = postRepository.findAllPostByUserId(id);

        if(posts.isEmpty()){
            throw new ApiExeption("Not found any post for this user: " + user.getUsername()); 
        }

        return posts; 
    }


    public Post getPostByTitle(String postTitle){
        Post post = postRepository.findPostByTitle(postTitle); 

        if(post == null){
            throw new ApiExeption("Not found any post by this title: " + postTitle); 
        }

        return post; 
    }

    public List<Post> getPostsBeforePublishedDate(LocalDate publishDate){
        return postRepository.findAllPostsBeforePublishDate(publishDate);
    }

    public void likePost(Integer userId, Integer postId){
        User user = userRepository.findUserById(userId); 
        Post post = postRepository.findPostById(postId); 

        if(user == null || post == null){
            throw new ApiExeption(user == null 
            ? "User with this ID: " + userId + ", Not found"
            : "Post with this ID: " + postId + ", Not found"); 
        }

        post.setLikePost(post.getLikePost() + 1);
        postRepository.save(post); 
    }

    public void disLikePost(Integer userId, Integer postId){
        User user = userRepository.findUserById(userId); 
        Post post = postRepository.findPostById(postId); 

        if(user == null || post == null){
            throw new ApiExeption(user == null 
            ? "User with this ID: " + userId + ", Not found"
            : "Post with this ID: " + postId + ", Not found"); 
        }

        post.setDisLikePost(post.getDisLikePost() + 1);
        postRepository.save(post); 
    }
}
