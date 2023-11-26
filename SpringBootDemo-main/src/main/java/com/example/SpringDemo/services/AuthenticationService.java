package com.example.SpringDemo.services;

import com.example.SpringDemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.example.SpringDemo.dto.JwtAuthenticationResponse;
import com.example.SpringDemo.dto.SignInRequest;
import com.example.SpringDemo.dto.SignUpRequest;
import com.example.SpringDemo.models.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = User
                .builder()
                .username(request.getUsername())
                .fullname(request.getFullname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("admin")
                .build();

        user = userService.create(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
    
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}