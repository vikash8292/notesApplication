package com.notesapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import com.notesapp.model.User;
import com.notesapp.model.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {


    //creating user
     User createUser(User user, Set<UserRole> userRoles) throws Exception;
    //get user by userName
     User getUser(String username);

    //get delete user by id
     void deleteUser(Long userId);
    
     Long getCountUser();
     
    List<Long> getUserIds();

}
