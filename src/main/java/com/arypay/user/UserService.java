package com.arypay.user;

import org.springframework.stereotype.Service;

import com.arypay.config.exceptions.UserDiscrepancyException;
import com.arypay.user.dto.reqNewUserDTO;
import com.arypay.user.dto.resNewUserDTO;
import com.arypay.wallet.WalletService;
import com.arypay.wallet.DTO.CreateWalletDTO;

import java.math.BigDecimal;
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
    private final WalletService walletService;

    @Transactional
    public Optional<UUID> create (String email, String password, Role role) {
        if (userRepository.existsByEmail(email) == false) {
            User user1 = new User();
            user1.setEmail(email);
            user1.setPassword(passwordEncoder.encode(password));
            user1.setRole(role);
            userRepository.save(user1);
            walletService.create(new CreateWalletDTO(user1.getId(),BigDecimal.valueOf(100)));
            return Optional.ofNullable(user1.getId());
        }
        return Optional.empty();
    }

    @Transactional
    public resNewUserDTO create (reqNewUserDTO dto) {

        if (userRepository.existsByEmail(dto.email())) {throw new UserDiscrepancyException("User already exists");}
        if (userRepository.existsByCPFJ(dto.CPFJ())) {throw new UserDiscrepancyException("CPFJ already in use");}

        User user1 = new User();
        user1.setEmail(dto.email());
        
        user1.setUsername(dto.username());
        user1.setCPFJ(dto.CPFJ());

        user1.setPassword(passwordEncoder.encode(dto.password()));
        user1.setRole(dto.role());
        userRepository.save(user1);
        UUID walletId = walletService.create(new CreateWalletDTO(user1.getId(),BigDecimal.valueOf(100)));
        return new resNewUserDTO(user1.getId(),walletId);
    }

}
