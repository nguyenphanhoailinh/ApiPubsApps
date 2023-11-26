package com.example.SpringDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {
    String token;
}
