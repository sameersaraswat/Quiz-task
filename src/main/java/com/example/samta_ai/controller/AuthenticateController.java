package com.example.samta_ai.controller;


import com.example.samta_ai.config.JwtUtils;
import com.example.samta_ai.model.JwtRequest;
import com.example.samta_ai.model.JwtResponse;
import com.example.samta_ai.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken (@RequestBody JwtRequest jwtRequest) {

        String username = jwtRequest.getUsername();
        String password = jwtRequest.getPassword();

        try {
            authenticate(username,password);
        }
        catch (UsernameNotFoundException e) {
            e.printStackTrace();
            System.out.println("User not found !!");
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("error " + e.getMessage());
        }

        // user is authenticated

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        final String token = this.jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String username,String password) {
        try {
            System.out.println("inside");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }
        catch(DisabledException e) {
            e.printStackTrace();
            System.out.println("User Disabled "+ e.getMessage());
        }
        catch(BadCredentialsException e) {
            e.printStackTrace();
            System.out.println("Invalid Credentials" + e.getMessage());
        }
    }

}
