package com.arypay.transaction;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arypay.config.TransactionNotFoundException;
import com.arypay.dto.GenericResponseDTO;
import com.arypay.transaction.dto.TransactionDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionRepository repository;
    private final TransactionService service;

    public TransactionController(TransactionRepository transactionRepository, TransactionService transactionService) {
        this.repository = transactionRepository;
        this.service = transactionService;
    }

    @GetMapping
    public List<TransactionDTO> listAll() {
        return repository.findAll().stream().map(
            target -> new TransactionDTO(target.getSender(), target.getReceiver(), target.getAmount()))
        .toList();
    }

    @GetMapping("/{id}")
    public GenericResponseDTO findById(@PathVariable("id") UUID id) {
        Transaction target = repository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction" + id + "not found"));
        return new GenericResponseDTO(String.format("TRANSACTION: %s > %s > %s",target.getSender(),target.getAmount(),target.getReceiver()));
    }

    @PostMapping("/new")
    public ResponseEntity<GenericResponseDTO> handleNewTransaction(@Valid @RequestBody TransactionDTO transaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(transaction));
    }
}