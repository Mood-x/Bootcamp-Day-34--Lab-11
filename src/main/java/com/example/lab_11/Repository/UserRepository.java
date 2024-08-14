package com.example.lab_11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab_11.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findUserById(Integer id); 

}
