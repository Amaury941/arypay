package com.arypay.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arypay.dto.GenericResponseDTO;
import com.arypay.producer.MessageProducer;
import com.arypay.transaction.dto.TransactionDTO;
import com.arypay.user.Role;
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
    private final MessageProducer messeger;

    @Transactional
    public GenericResponseDTO create (TransactionDTO dto){

        Wallet wallet1 = walletRepository.findByHolderForUpdate(dto.payer())
                .orElse(null);
        if (wallet1 == null) {
            return new GenericResponseDTO("wallet de sender não encontrada");
        }

        if ( wallet1.getBalance().compareTo(dto.value()) < 0 ) {return new GenericResponseDTO("Saldo insuficiente");}

        Role role1 = userRepository.findRoleById(wallet1.getHolder()).orElse(null);
        if (role1 == null || role1 != Role.COMMON) {
            return new GenericResponseDTO("sender não autorizado");
        }

        Wallet wallet2 = walletRepository.findByHolderForUpdate(dto.payee())
                .orElse(null);
        if (wallet2 == null) {
            return new GenericResponseDTO("wallet de receiver não encontrada");
        }

        Role role2 = userRepository.findRoleById(wallet2.getHolder()).orElse(null);
        if (role2 == null || role2 != Role.MERCHANT){
            return new GenericResponseDTO("receiver não autorizado");
        }

        Transaction transaction = new Transaction();
        transaction.setSender(wallet1.getId());
        transaction.setReceiver(wallet2.getId());
        transaction.setAmount(dto.value());

        messeger.SendMessage("transfer-exchange", "new", transaction);

        wallet1.setBalance(wallet1.getBalance().subtract(dto.value()));
        wallet2.setBalance(wallet2.getBalance().add(dto.value()));


        Transaction saved = transactionRepository.save(transaction);

        return new GenericResponseDTO("id: " + saved.getId());        
    }
}
