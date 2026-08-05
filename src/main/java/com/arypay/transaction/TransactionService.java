package com.arypay.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arypay.dto.GenericResponseDTO;
import com.arypay.transaction.dto.TransactionDTO;
import com.arypay.user.Role;
import com.arypay.user.User;
import com.arypay.user.UserRepository;
import com.arypay.wallet.Wallet;
import com.arypay.wallet.WalletRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public GenericResponseDTO create (TransactionDTO dto){

        User sender = userRepository.findByIdForUpdate(dto.sender())
                .orElse(null);
        if (sender == null) {
            return new GenericResponseDTO("sender não encontrado");
        }
        Wallet wallet1 = walletRepository.findByIdForUpdate(dto.sender())
                .orElse(null);
        if (wallet1 == null) {
            return new GenericResponseDTO("wallet de sender não encontrada");
        }

        if ( wallet1.getBalance().compareTo(dto.amount()) < 0 ) {return new GenericResponseDTO("Saldo insuficiente");}

        User receiver = userRepository.findByIdForUpdate(dto.receiver())
                .orElse(null);
        if (receiver == null) {
            return new GenericResponseDTO("Receiver não encontrado");
        }
        Wallet wallet2 = walletRepository.findByIdForUpdate(dto.receiver())
                .orElse(null);
        if (wallet2 == null) {
            return new GenericResponseDTO("wallet de receiver não encontrada");
        }

        if (sender.getRole() != Role.COMMON) {
            return new GenericResponseDTO("sender não autorizado");
        }
        if (receiver.getRole() != Role.MERCHANT) {
            return new GenericResponseDTO("receiver não autorizado");
        }

        Transaction transaction = new Transaction();
        transaction.setSender(sender.getId());
        transaction.setReceiver(receiver.getId());
        transaction.setAmount(dto.amount());

        Transaction saved = transactionRepository.save(transaction);

        return new GenericResponseDTO("id: " + saved.getId());        
    }
}
