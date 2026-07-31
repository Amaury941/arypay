package com.arypay.transaction;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public List<GenericResponseDTO> listAll() {
        return repository.findAll().stream().map(
            target -> new GenericResponseDTO(String.format(String.format("%s %s",target.getId(), target.getAmount())))
        )
        .toList();
    }

    @GetMapping("/{id}")
    public GenericResponseDTO findById(@PathVariable("id") UUID id) {
        Transaction target = repository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction" + id + "not found"));
        return new GenericResponseDTO(String.format("TRANSACTION: %s > %s > %s",target.getSender(),target.getAmount(),target.getReceiver()));
    }
}