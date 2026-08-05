package com.arypay.wallet;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  WalletRepository extends JpaRepository<Wallet,UUID> {
    boolean existsById (UUID id);
    
    boolean existsByHolder(UUID holder);
}
