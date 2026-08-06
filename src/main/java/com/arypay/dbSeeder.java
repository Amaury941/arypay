package com.arypay;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.arypay.user.Role;
import com.arypay.user.UserService;
import com.arypay.wallet.WalletService;
import com.arypay.wallet.DTO.CreateWalletDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class dbSeeder implements CommandLineRunner {

    private final UserService userService;
    private final WalletService walletService;

    @Override
    public void run(String... args) {

        Optional<UUID> user1 = userService.create("john@demo.com","john@demo.com",Role.COMMON);
        if (user1.isPresent()) {System.out.println("SEED: created First user");walletService.create(new CreateWalletDTO(user1.get(),BigDecimal.valueOf(100)));}
        Optional<UUID> user2 = userService.create("jane@demo.com","jane@demo.com",Role.MERCHANT);
        if (user2.isPresent()) {System.out.println("SEED: created Second user");walletService.create(new CreateWalletDTO(user2.get(),BigDecimal.valueOf(0)));}
        
    }
    
}
