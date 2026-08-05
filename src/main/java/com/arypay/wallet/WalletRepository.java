package com.arypay.wallet;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;

@Repository
public interface  WalletRepository extends JpaRepository<Wallet,UUID> {
    Optional<Wallet> findById(UUID id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<Wallet> findByIdForUpdate(UUID id);

    boolean existsById (UUID id);
    
    boolean existsByHolder(UUID holder);
}
