package com.arypay;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.arypay.user.Role;
import com.arypay.user.UserService;
import com.arypay.user.dto.reqNewUserDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class dbSeeder implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) {
        try {
            userService.create(new reqNewUserDTO("john@demo.com", "john@demo.com", Role.COMMON));
            userService.create(new reqNewUserDTO("jane@demo.com", "jane@demo.com", Role.MERCHANT));
        }
        catch (RuntimeException r){
            // do nothing
        }
    }
    
}
