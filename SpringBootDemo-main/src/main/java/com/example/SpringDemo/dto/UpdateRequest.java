package com.example.SpringDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateRequest {
	String username;
    String password;
    String fullname;
}