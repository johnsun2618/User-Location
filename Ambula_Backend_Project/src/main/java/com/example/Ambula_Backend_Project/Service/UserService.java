package com.example.Ambula_Backend_Project.Service;

import com.example.Ambula_Backend_Project.Entity.User;
import com.example.Ambula_Backend_Project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String post( User user)
    {
        // Implement this logic to update a user
        userRepository.save(user);
        return "Create User";
    }

    public List<User> getUser(){
        // Implement this logic to retrieve all user
        return userRepository.findAll();
    }

    public User getUserById(long id)
    {
        // Implement this logic to retrieve a user by ID
        return userRepository.findById(id).get();
    }

}

