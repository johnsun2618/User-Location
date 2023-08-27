package com.example.Ambula_Backend_Project.Repository;

import com.example.Ambula_Backend_Project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
