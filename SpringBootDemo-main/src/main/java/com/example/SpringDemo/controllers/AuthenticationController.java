package com.example.SpringDemo.controllers;

import com.example.SpringDemo.dto.JwtAuthenticationResponse;
import com.example.SpringDemo.dto.SignInRequest;
import com.example.SpringDemo.dto.SignUpRequest;
import com.example.SpringDemo.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        try {
            JwtAuthenticationResponse response = authenticationService.signUp(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
        try {
            JwtAuthenticationResponse response = authenticationService.signIn(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
