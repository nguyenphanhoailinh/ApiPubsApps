package com.example.SpringDemo.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class SignInRequest {
    String username;
    String password;
}