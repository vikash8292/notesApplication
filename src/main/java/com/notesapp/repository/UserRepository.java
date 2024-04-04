package com.notesapp.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.notesapp.model.User;


public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);
    
    @Query("SELECT distinct u.id from User u")
    List<Long> getUserIds();
  
}
