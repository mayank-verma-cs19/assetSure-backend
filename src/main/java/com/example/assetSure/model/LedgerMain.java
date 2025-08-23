package com.example.assetSure.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ledger_main_table")
@Getter
@Setter
public class LedgerMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "father_name")
    private String fatherName;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String phone;

    private Double amount;

    @Column(name = "estimate_days")
    private Integer estimateDays;

    private Double roi;

    private LocalDate date;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String status;

    @Column(name = "repayment_amount")
    private Double repaymentAmount;

    @Column(name = "interest_charged")
    private Double interestCharged;

    @Column(name = "closed_on")
    private LocalDateTime closedOn;

    @Column(name = "closed_by_name")
    private String closedByName;

    @Column(name = "closed_by_contact")
    private String closedByContact;

    @Column(name = "final_comments", columnDefinition = "TEXT")
    private String finalComments;

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

    @OneToMany(mappedBy = "ledger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollateralDeposit> collaterals = new ArrayList<>();

    @PrePersist
    public void onCreate() {
        createdOn = LocalDateTime.now();
        if (isDeleted == null) isDeleted = false;
    }

    @PreUpdate
    public void onUpdate() {
        updatedOn = LocalDateTime.now();
    }
}
