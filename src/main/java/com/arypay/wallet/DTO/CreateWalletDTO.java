package com.arypay.wallet.DTO;
import java.util.UUID;
import java.math.BigDecimal;

public record CreateWalletDTO(
    UUID holder,
    BigDecimal balance 
) {
} 
