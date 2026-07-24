package com.arypay.config.seed;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.arypay.user.Role;
import com.arypay.user.User;
import com.arypay.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class seedUsers implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String mock1 = "john@demo.com";
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",userRepository.ExistsByEmail(mock1));
        if (userRepository.ExistsByEmail(mock1) == false) {
            User user1 = new User();
            user1.setEmail(mock1);
            user1.setPassword(passwordEncoder.encode(mock1));
            user1.setRole(Role.COMMON);
        }

        String mock2 = "jane@demo.com";
        
        if (userRepository.ExistsByEmail(mock2) == false) {
            User user2 = new User();
            user2.setEmail(mock2);
            user2.setPassword(passwordEncoder.encode(mock2));
            user2.setRole(Role.MERCHANT);
        }
    }
    
}
