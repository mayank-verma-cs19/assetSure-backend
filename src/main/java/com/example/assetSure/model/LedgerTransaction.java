package com.example.assetSure.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_type")
@Getter
@Setter
public class LedgerTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ledger_id", nullable = false)
    private LedgerMain ledgerMain;

    @Column(name = "transaction_type")
    private String transactionType; // e.g. "REPAYMENT", "INTEREST_CHARGE"

    private Double amount;

    private Double interest;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    // Getters and Setters ...

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
}
