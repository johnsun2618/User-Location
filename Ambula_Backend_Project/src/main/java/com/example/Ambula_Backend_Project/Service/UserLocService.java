package com.example.Ambula_Backend_Project.Service;

import com.example.Ambula_Backend_Project.Entity.UserLocation;
import com.example.Ambula_Backend_Project.Repository.UserLocRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLocService {

    @Autowired
    UserLocRepository userLocRepository;

    public UserLocation postUserLocation(UserLocation userLocation) {
        // Implement this logic to create a user location
        return userLocRepository.save(userLocation);
    }

    public UserLocation updateUserLocation(Long id, UserLocation updatedUserLocation) {
        // Implement this logic to update a user location
        UserLocation existingUserLocation = userLocRepository.findById(id).orElse(null);
        if (existingUserLocation != null) {
            // Update fields as needed
            existingUserLocation.setName(updatedUserLocation.getName());
            existingUserLocation.setLatitude(updatedUserLocation.getLatitude());
            existingUserLocation.setLongitude(updatedUserLocation.getLongitude());
            existingUserLocation.setExcluded(updatedUserLocation.isExcluded());

            return userLocRepository.save(existingUserLocation);
        }
        // Return null if the user location doesn't exist
        return null;
    }

    public UserLocation getUserLocationById(Long id) {
        // Implement this logic to retrieve a user location by ID
        return userLocRepository.findById(id).orElse(null);
    }

    public List<UserLocation> getNearestUsersFromLocation(double latitude, double longitude, int N) {
        // Implement this logic to find the nearest N users from the given location (0,0)
        return userLocRepository.findNearestUsers(latitude, longitude, false, N);
    }
}

