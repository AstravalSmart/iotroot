package com.astraval.iotroot.controller;

import com.astraval.iotroot.model.User;
import com.astraval.iotroot.service.AuthService;
import com.astraval.iotroot.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

 @PostMapping("/register")
public User register(@RequestBody User user) {
    return userService.register(user);
}

@PostMapping("/login")
public String login(@RequestBody User user) {
    boolean ok = authService.login(user.getEmail(), user.getPassword());
    return ok ? "Login success" : "Invalid credentials";
}

}
