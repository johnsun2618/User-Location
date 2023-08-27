package com.example.Ambula_Backend_Project.Repository;

import com.example.Ambula_Backend_Project.Entity.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLocRepository extends JpaRepository<UserLocation, Long> {

    @Query(value = "SELECT * FROM user_location " +
            "WHERE excluded = :excluded " +
            "ORDER BY " +
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(latitude)) " +
            "* cos(radians(longitude) - radians(:longitude)) + sin(radians(:latitude)) " +
            "* sin(radians(latitude)))) " +
            "LIMIT :n", nativeQuery = true)
    List<UserLocation> findNearestUsers(@Param("latitude") double latitude,
                                        @Param("longitude") double longitude,
                                        @Param("excluded") boolean excluded,
                                        @Param("n") int n);

}
