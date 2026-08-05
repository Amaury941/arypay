package com.arypay.wallet;

import org.springframework.stereotype.Service;

import com.arypay.dto.GenericResponseDTO;
import com.arypay.user.UserRepository;
import com.arypay.wallet.DTO.CreateWalletDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository repository; 
    private final UserRepository userRepository;
    @Transactional
    public GenericResponseDTO create (CreateWalletDTO target) {
        if ( userRepository.findByIdForUpdate(target.holder()).isPresent() && !repository.existsByHolder(target.holder())) {
            repository.save(new Wallet(target.holder(),target.balance()));
        }
        return new GenericResponseDTO("done.");
    }
    // TODO: generate subract operation

    // TODO: generate add operation

}
