package com.arypay.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arypay.dto.GenericResponseDTO;
import com.arypay.transaction.dto.TransactionDTO;
import com.arypay.user.Role;
import com.arypay.user.User;
import com.arypay.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Transactional
    public GenericResponseDTO create (TransactionDTO dto){

        User sender = userRepository.findByIdForUpdate(dto.sender())
                .orElse(null);
        if (sender == null) {
            return new GenericResponseDTO("sender não encontrado");
        }

        User receiver = userRepository.findByIdForUpdate(dto.receiver())
                .orElse(null);
        if (receiver == null) {
            return new GenericResponseDTO("Receiver não encontrado");
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
