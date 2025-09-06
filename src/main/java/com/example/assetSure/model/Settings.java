package com.example.assetSure.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "settings")
public class Settings {

    @Id
    private Long id = 1L;  // Singleton settings row

    private Double roi;

    @Column(name = "estimate_days")
    private Integer estimateDays;

    @Column(name = "default_lender_id")
    private Long defaultLenderId;

    @Column(name = "enable_popup")
    private Boolean enablePopup;  // default ON

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @PrePersist
    protected void onCreate() {
        createdOn = LocalDateTime.now();
        updatedOn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedOn = LocalDateTime.now();
    }
}