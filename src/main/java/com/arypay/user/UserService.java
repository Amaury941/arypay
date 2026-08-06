package com.arypay.user;

import org.springframework.stereotype.Service;

import com.arypay.dto.GenericResponseDTO;
import com.arypay.user.dto.NewUserDTO;

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

    @Transactional
    public GenericResponseDTO create (NewUserDTO dto) {
        if (userRepository.existsByEmail(dto.email()) == false) {
            User user1 = new User();
            user1.setEmail(dto.email());
            user1.setPassword(passwordEncoder.encode(dto.password()));
            user1.setRole(Role.COMMON);
            userRepository.save(user1);
            return new GenericResponseDTO(""+user1.getId());
        }
        return new GenericResponseDTO("User already exists! email has to be unique!");
    }

}
