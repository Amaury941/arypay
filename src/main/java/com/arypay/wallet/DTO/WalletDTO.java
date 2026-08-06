package com.arypay.wallet.DTO;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletDTO (
    UUID id,
    UUID holder,
    BigDecimal balance
) {
}
