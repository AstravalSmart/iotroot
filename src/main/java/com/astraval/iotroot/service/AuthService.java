package com.astraval.iotroot.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public String login(String email, String password) {
        return userService.validateAndGetUserId(email, password);
    }
}
