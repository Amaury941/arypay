package com.arypay.transaction.dto;

import java.math.BigDecimal;

public record newRequestDTO (
    //@UUID (message = "sender not informed")
    java.util.UUID sender,
    //@UUID (message = "receiver not informed")
    java.util.UUID receiver,
    BigDecimal amount
){}
