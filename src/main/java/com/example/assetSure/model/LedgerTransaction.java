package com.example.assetSure.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ledger_transactions")
@Getter
@Setter
public class LedgerTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ledger_id", nullable = false)
    private LedgerMain ledgerMain;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    private BigDecimal amount;

    private BigDecimal interest = BigDecimal.ZERO;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status = TransactionStatus.ACTIVE;

    @PrePersist
    public void onCreate() {
        createdOn = LocalDateTime.now();
        if (transactionDate == null) transactionDate = LocalDateTime.now();
        if (isDeleted == null) isDeleted = false;
    }

    @PreUpdate
    public void onUpdate() {
        updatedOn = LocalDateTime.now();
    }

    public enum TransactionType {
        CREDIT,
        DEBIT
    }

    public enum TransactionStatus {
        ACTIVE,
        PENDING,
        SETTLED,
        CANCELLED
    }
}
