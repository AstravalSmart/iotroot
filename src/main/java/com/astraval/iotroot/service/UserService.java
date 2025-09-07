package com.astraval.iotroot.service;

import com.astraval.iotroot.model.User;
import com.astraval.iotroot.repo.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public List<User> getAll() {
        return repo.findAll();
    }
public boolean validate(String email, String rawPassword) {
    return repo.findByEmail(email)
               .map(u -> encoder.matches(rawPassword, u.getPassword()))
               .orElse(false);
    }
}
