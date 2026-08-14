package com.arypay.wallet;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.arypay.config.exceptions.UserDiscrepancyException;
import com.arypay.dto.GenericResponseDTO;
import com.arypay.transaction.dto.TransactionDTO;
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
    public UUID create (CreateWalletDTO target) throws UserDiscrepancyException {
        
        userRepository.findByIdForUpdate(target.holder()).orElseThrow(() -> new UserDiscrepancyException("Holder not found"));

        if (repository.existsByHolder(target.holder())) {throw new UserDiscrepancyException("Holder already has an account");};

        Wallet wallet = new Wallet(target.holder(),target.balance()); 
        repository.save(wallet);
        
        return wallet.getId();
    }

    @Transactional
    public GenericResponseDTO updateWalletBalance(TransactionDTO dto) {
        Wallet sender = repository.findByHolderForUpdate(dto.payer())
                .orElseThrow();
        Wallet receiver = repository.findByHolderForUpdate(dto.payee())
                .orElseThrow();

        sender.setBalance(sender.getBalance().subtract(dto.value()));
        receiver.setBalance(receiver.getBalance().add(dto.value()));

        return new GenericResponseDTO("done");
    };
}
