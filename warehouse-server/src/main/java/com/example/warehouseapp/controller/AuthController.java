package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.User;
import com.example.warehouseapp.payload.LoginDTO;
import com.example.warehouseapp.repository.UserRepository;
import com.example.warehouseapp.security.CurrentUser;
import com.example.warehouseapp.security.JwtProvider;
import com.example.warehouseapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
//@CrossOrigin
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;


//    @PostMapping("/register")


    @GetMapping("/me")
    public HttpEntity<?> getUser(@CurrentUser User user) {
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword()));

        User user = (User) authenticate.getPrincipal();

        String token = jwtProvider.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }

    //editPhone
    //editpassword

    //Shaxzod


}
