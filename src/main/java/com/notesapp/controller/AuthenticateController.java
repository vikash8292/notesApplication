package com.notesapp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.notesapp.config.JwtUtils;
import com.notesapp.model.JwtRequest;
import com.notesapp.model.JwtResponse;
import com.notesapp.model.User;
import com.notesapp.service.impl.UserDetailsServiceImpl;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    //generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
        	System.out.println(jwtRequest.getUsername()+":"+jwtRequest.getPassword());
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());

        }catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("User not found");
        }
        //athenticated user
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        
        return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String username,String password) throws Exception {
    	System.out.println("in authenticate ");
        try {
            System.out.println("status: "+ authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password)));

        }catch (DisabledException e)
        {
        	e.printStackTrace();
            throw new Exception("USER DUSABLED"+e.getMessage());
        }catch (BadCredentialsException e){
        	e.printStackTrace();
        	throw new Exception("Invalid Credentials"+e.getMessage());
        }
        System.out.println("reached here");
    }
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal)
    {
       return ((User)this.userDetailsService.loadUserByUsername(principal.getName()));
    }
}
