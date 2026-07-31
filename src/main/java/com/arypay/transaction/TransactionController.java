package com.arypay.transaction;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arypay.config.TransactionNotFoundException;
import com.arypay.dto.GenericResponseDTO;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionRepository repository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.repository = transactionRepository;
    }

    public GenericResponseDTO findById(@PathVariable("id") UUID id) {
        Transaction target = repository.findById(id).orElseThrow(() -> new TransactionNotFoundException("transação não encontrada"));

        return new GenericResponseDTO(String.format("%s %s",target.getId(), target.getAmount()));
    }



}
