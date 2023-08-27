package com.example.Ambula_Backend_Project.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user_location")
public class UserLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLocId;

    @Column(name = "userName")
    private String name;

    @Column(name = "userLatitude")
    private double latitude;

    @Column(name = "userLongitude")
    private double longitude;

    @Column(name = "userExcluded")
    private boolean excluded;

}
