package com.arypay.transaction.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.UUID;

import jakarta.validation.constraints.NotBlank;

public record newTransactionDTO (
    @NotBlank @UUID (message = "sender not informed")
    String sender,
    @NotBlank @UUID (message = "receiver not informed")
    String receiver,
    BigDecimal amount
){}
