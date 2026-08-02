package com.arypay.transaction.dto;

import java.math.BigDecimal;

import java.util.UUID;

public record newTransactionDTO (
    UUID sender,
    UUID receiver,
    BigDecimal amount
){}
