package com.arypay.user;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Optional<UUID> create (String email, String password, Role role) {
        if (userRepository.existsByEmail(email) == false) {
            User user1 = new User();
            user1.setEmail(email);
            user1.setPassword(passwordEncoder.encode(password));
            user1.setRole(role);
            userRepository.save(user1);
            return Optional.ofNullable(user1.getId());
        }
        return Optional.empty();
    }
}
