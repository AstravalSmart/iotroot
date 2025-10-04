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
    try {
        User authenticatedUser = authService.authenticate(user.getEmail(), user.getPassword());
        if (authenticatedUser != null) {
            return Map.of(
                "success", true, 
                "userId", authenticatedUser.getId(), 
                "email", authenticatedUser.getEmail(),
                "username", authenticatedUser.getEmail(),
                "message", "Login successful"
            );
        }
        return Map.of("success", false, "message", "Invalid email or password");
    } catch (Exception e) {
        return Map.of("success", false, "message", "Login failed: " + e.getMessage());
    }
}

}
