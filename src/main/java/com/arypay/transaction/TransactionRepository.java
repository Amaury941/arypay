package com.arypay.transaction;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,UUID> {
    boolean existsById(UUID id); 
    Optional<Transaction> findById (UUID id);
}
