package com.arypay.transaction.dto;

import java.math.BigDecimal;

public record TransactionDTO (
    String sender,
    String receiver,
    BigDecimal amount
){}
