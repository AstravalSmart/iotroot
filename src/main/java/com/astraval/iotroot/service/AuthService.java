package com.astraval.iotroot.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public boolean login(String username, String password) {
        return userService.validate(username, password);
    }
}
