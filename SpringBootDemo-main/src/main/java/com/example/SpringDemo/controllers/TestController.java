package com.example.SpringDemo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/all")
    public String allEndPoint() {
        return "everyone can see this";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('user')")
    public String usersEndPoint() {
        return "ONLY users can see this";
    }

    @GetMapping("/admins")
    @PreAuthorize("hasAuthority('admin')")
    public String adminsEndPoint() {
        return "ONLY admins can see this";
    }
}