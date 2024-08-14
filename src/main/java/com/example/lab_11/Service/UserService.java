package com.example.lab_11.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lab_11.API.ApiExeption;
import com.example.lab_11.Model.User;
import com.example.lab_11.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; 

    public List<User> getUsers(){
        if(userRepository.findAll().isEmpty()){
            throw new ApiExeption("Not found users"); 
        }
        return userRepository.findAll(); 
    }

    public User getUserById(Integer id){
        User user = userRepository.findUserById(id); 

        if(user == null){
            throw new ApiExeption("User with this ID: " + id + ", Not found"); 
        }
        return user; 
    }


    public void addUser(User user){
        userRepository.save(user); 
    }

    public void updateUser(Integer id, User updateUser){
        User user = userRepository.findUserById(id); 

        if(user == null){
            throw new ApiExeption("User with this ID: " + id + ", Not found"); 
        }

        user.setUsername(updateUser.getUsername());
        user.setPassword(updateUser.getPassword());
        user.setEmail(updateUser.getEmail());
        userRepository.save(user); 
    }


    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id); 

        if(user == null){
            throw new ApiExeption("User with this ID: " + id + ", Not found"); 
        }
        userRepository.delete(user); 
    }

    public void followUser(Integer followerId, Integer followingId){
        User follower = userRepository.findUserById(followerId); 
        User following = userRepository.findUserById(followerId); 

        if(follower == null || following == null){
            throw  new ApiExeption(follower == null 
            ? "Follower with this ID: "+ followerId + ", Not found"
            :"Following with this ID: "+ followingId + ", Not found"); 
        }

        follower.getFollowing().add(followingId); 
        userRepository.save(follower); 

        following.getFollowers().add(followerId); 
        userRepository.save(following); 
    }

    public void unFollowUser(Integer followerId, Integer followingId){
        User follower = userRepository.findUserById(followerId); 
        User following = userRepository.findUserById(followerId); 

        if(follower == null || following == null){
            throw  new ApiExeption(follower == null 
            ? "Follower with this ID: "+ followerId + ", Not found"
            :"Following with this ID: "+ followingId + ", Not found"); 
        }

        follower.getFollowing().remove(followingId); 
        userRepository.save(follower); 

        following.getFollowers().remove(followerId); 
        userRepository.save(following); 
    }
}
