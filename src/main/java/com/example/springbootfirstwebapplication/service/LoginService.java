package com.example.springbootfirstwebapplication.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
    public boolean validateUser(String name, String password) {
        return (name.equalsIgnoreCase("in28minutes") && password.equals("dummy"));
    }
}