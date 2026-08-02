package com.arypay.transaction;

import org.springframework.stereotype.Service;

import com.arypay.dto.GenericResponseDTO;
import com.arypay.transaction.dto.TransactionDTO;
import com.arypay.transaction.dto.newTransactionDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;


    public TransactionDTO create (newTransactionDTO dto){

        Transaction transaction = new Transaction();
        transaction.setSender(dto.sender());
        transaction.setReceiver(dto.receiver());
        transaction.setAmount(dto.amount());

        Transaction saved = transactionRepository.save(transaction);
        
        return new TransactionDTO(saved.getSender(),saved.getReceiver(),saved.getAmount());
    }

}
