package com.example.assetSure.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
    private Double estWeight;

    @Column(name = "notes")
    private String notes;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_on", updatable = false)
    private String createdOn;

    @Column(name = "updated_on")
    private String updatedOn;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_by_id")
    private Long createdById;

    // Automatically set values before insert
    @PrePersist
    public void onCreate() {
        if (isActive == null) isActive = true;
        if (isDeleted == null) isDeleted = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        createdOn = ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).format(formatter);
    }

    // Automatically update timestamp on update
    @PreUpdate
    public void onUpdate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        updatedOn = ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).format(formatter);
    }
}
