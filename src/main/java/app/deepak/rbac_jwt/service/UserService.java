package app.deepak.rbac_jwt.service;


import java.time.LocalDateTime;
import java.util.Optional;
import javax.transaction.Transactional;

import app.deepak.rbac_jwt.model.User;
import app.deepak.rbac_jwt.repository.UserRepository;
import app.deepak.rbac_jwt.request.CreateUserRequest;
import app.deepak.rbac_jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    public UserService() {
    }

    public User getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    @Transactional
    public User updateUser(CreateUserRequest input) {
        User existingUser = userRepository.findByEmail(input.getEmail()).orElseThrow(() -> {
            return new RuntimeException("User not found");
        });
        if (input.getName() != null) {
            existingUser.setName(input.getName());
        }

        if (input.getEmail() != null) {
            existingUser.setEmail(input.getEmail());
        }

        if (input.getPassword() != null) {
            existingUser.setPassword(input.getPassword());
        }

        existingUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(existingUser);
    }

    @Transactional
    public String deleteUser(String email) {
        User existingUser = userRepository.findByEmail(email).orElseThrow(() -> {
            return new RuntimeException("User not found");
        });
        userRepository.delete(existingUser);
        return email + " deleted successfully";
    }
}