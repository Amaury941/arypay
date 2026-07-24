package com.arypay.transaction;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String sender;

    private String receiver;

    private BigDecimal amount;
}
