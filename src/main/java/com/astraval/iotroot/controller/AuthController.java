package com.astraval.iotroot.controller;

import com.astraval.iotroot.model.User;
import com.astraval.iotroot.service.AuthService;
import com.astraval.iotroot.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

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
public Object register(@RequestBody User user) {
    try {
        return userService.register(user);
    } catch (RuntimeException e) {
        return Map.of("error", e.getMessage());
    }
}

@PostMapping("/login")
public Map<String, Object> login(@RequestBody User user) {
    String userId = authService.login(user.getEmail(), user.getPassword());
    if (userId != null) {
        return Map.of("success", true, "userId", userId, "message", "Login success");
    }
    return Map.of("success", false, "message", "Invalid credentials");
}

}
