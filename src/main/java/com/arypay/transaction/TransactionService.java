package com.arypay.transaction;

import org.springframework.stereotype.Service;

import com.arypay.dto.GenericResponseDTO;
import com.arypay.transaction.dto.newTransactionDTO;
import com.arypay.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;


    public GenericResponseDTO create (newTransactionDTO dto){

        if ( userRepository.findById(dto.sender()).isPresent() && userRepository.findById(dto.receiver()).isPresent() ) {
            Transaction transaction = new Transaction();
            transaction.setSender(dto.sender());
            transaction.setReceiver(dto.receiver());
            transaction.setAmount(dto.amount());
            Transaction saved = transactionRepository.save(transaction);
            return new GenericResponseDTO("id: "+saved.getId());
        }        
        return new GenericResponseDTO("sender e/ou receiver não encontrados");
    }

}
