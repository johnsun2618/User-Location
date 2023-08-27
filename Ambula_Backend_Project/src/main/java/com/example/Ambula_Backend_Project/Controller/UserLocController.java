package com.example.Ambula_Backend_Project.Controller;

import com.example.Ambula_Backend_Project.Entity.UserLocation;
import com.example.Ambula_Backend_Project.Service.UserLocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userLocations")
public class UserLocController {

    @Autowired
    UserLocService userLocService;

    @PreAuthorize("hasRole('ADMIN')") // Implement this annotation to ensure only "ADMIN" could access this data
    @PostMapping("/create_data")
    public ResponseEntity<UserLocation> postUserLocation(@RequestBody UserLocation userLocation) {
        UserLocation createdUserLocation = userLocService.postUserLocation(userLocation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserLocation);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update_data/{id}")
    public ResponseEntity<UserLocation> updateUserLocation(@PathVariable Long id,
                                                           @RequestBody UserLocation userLocation) {
        UserLocation updatedUserLocation = userLocService.updateUserLocation(id, userLocation);
        if (updatedUserLocation != null) {
            return ResponseEntity.ok(updatedUserLocation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('READER')")
    @GetMapping("/get_data/{id}")
    public ResponseEntity<UserLocation> getUserLocationById(@PathVariable Long id) {
        UserLocation userLocation = userLocService.getUserLocationById(id);
        if (userLocation != null) {
            return ResponseEntity.ok(userLocation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('READER')")
    @GetMapping("/get_users/{N}")
    public ResponseEntity<List<UserLocation>> getUsersNearLocation(@PathVariable int N) {
        // Implement this logic to retrieve the nearest N users from (0,0)
        List<UserLocation> nearestUsers = userLocService.getNearestUsersFromLocation(0.0, 0.0, N);
        return ResponseEntity.ok(nearestUsers);
    }
}

