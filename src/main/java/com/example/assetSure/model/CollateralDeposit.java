package com.example.assetSure.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "collateral_deposite")
@Getter
@Setter
public class CollateralDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ledger_id", nullable = false)
    private LedgerMain ledgerMain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collateral_master_id", nullable = false)
    private CollateralMaster collateralMaster;

    private Double weight;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "is_returned")
    private Boolean isReturned = false;

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


    @PrePersist
    public void onCreate() {
        createdOn = LocalDateTime.now();
        if (isReturned == null) isReturned = false;
        if (isDeleted == null) isDeleted = false;
    }

    @PreUpdate
    public void onUpdate() {
        updatedOn = LocalDateTime.now();
    }
}
