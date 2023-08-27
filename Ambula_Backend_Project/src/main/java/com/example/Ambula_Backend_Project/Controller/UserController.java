package com.example.Ambula_Backend_Project.Controller;


import com.example.Ambula_Backend_Project.Entity.User;
import com.example.Ambula_Backend_Project.Repository.UserRepository;
import com.example.Ambula_Backend_Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')") // Implement this annotation to ensure only "ADMIN" could access this data
    @PostMapping(path = "/create_data")
    public String postUser(@RequestBody User user){
        return userService.post(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/getUsers")
    public List<User> getUsers(){
        return userService.getUser();
    }

    @GetMapping(path = "/getUsers/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/update_data/{id}")
    public ResponseEntity<User> patchUserById(@PathVariable Long id, @RequestBody User userDetails){
        User patch = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Exists with this Id: " + id));

        patch.setName(patch.getName());
        patch.setPassword(patch.getPassword());

        userRepository.save(userDetails);
        return ResponseEntity.ok(patch);


    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not Exists with this id: " + id));

        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}

