package com.notesapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notesapp.model.Role;
import com.notesapp.model.User;
import com.notesapp.model.UserRole;
import com.notesapp.repository.RoleRepository;
import com.notesapp.repository.UserRepository;
import com.notesapp.service.UserService;

import java.util.List;
import java.util.Set;
@Service
public class UserServiceImpl implements UserService {



	   @Autowired
	    private UserRepository userRepository;
	    @Autowired
	    private RoleRepository roleRepository;

	    //creating user
	    @Override
	    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
	        User local=this.userRepository.findByUsername(user.getUsername());
	        if(local!=null) {
	            System.out.println("User already exist!!");
	            throw new Exception("User already exist!!");
	        }
	        else {
	            //user create
	            for(UserRole ur:userRoles)
	            {
	                roleRepository.save(ur.getRole());
	            }
	            user.getUserRoles().addAll(userRoles);
	            local= this.userRepository.save(user);
	        }
	        return local;
	    }

	    //getting user by username
	    @Override
	    public User getUser(String username) {
	        User user = this.userRepository.findByUsername(username);
	        if (user != null) {
	            Set<UserRole> userRoles = user.getUserRoles();
	            for (UserRole userRole : userRoles) {
	                Role role = userRole.getRole();
	                if (role != null) {
	                    user.setAuthority(role.getRoleName());
	                    break;
	                }
	            }
	        }
	        return user;

	        // return this.userRepository.findByUsername(username);
	    }

	    @Override
	    public void deleteUser(Long userId) {
	        this.userRepository.deleteById(userId);
	    }

	    @Override
	    public Long getCountUser() {
	        return this.userRepository.count();
	    }

		@Override
		public List<Long> getUserIds() {
			return userRepository.getUserIds();
		}

}
