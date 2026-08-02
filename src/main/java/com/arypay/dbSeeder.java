package com.arypay;


import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.arypay.transaction.Transaction;
import com.arypay.transaction.TransactionRepository;
import com.arypay.user.Role;
import com.arypay.user.User;
import com.arypay.user.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class dbSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        String mock1 = "john@demo.com";
        if (userRepository.existsByEmail(mock1) == false) {
            User user1 = new User();
            user1.setEmail(mock1);
            user1.setPassword(passwordEncoder.encode(mock1));
            user1.setRole(Role.COMMON);
            userRepository.save(user1);
        }

        String mock2 = "jane@demo.com";
        
        if (userRepository.existsByEmail(mock2) == false) {
            User user2 = new User();
            user2.setEmail(mock2);
            user2.setPassword(passwordEncoder.encode(mock2));
            user2.setRole(Role.MERCHANT);
            userRepository.save(user2);
        }
        
    }
    
}
