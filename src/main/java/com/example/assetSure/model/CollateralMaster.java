package com.example.assetSure.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "collateral_master")
public class CollateralMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String material;

    private Double purity;

    @Column(name = "est_weight")
    private Double estimatedWeight;

    @Column(name = "is_active")
    private Boolean isActive = true;

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
        if (isActive == null) isActive = true;
        if (isDeleted == null) isDeleted = false;
    }

    @PreUpdate
    public void onUpdate() {
        updatedOn = LocalDateTime.now();
    }
}

