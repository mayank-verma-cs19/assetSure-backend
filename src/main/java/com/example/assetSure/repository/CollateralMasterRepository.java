package com.example.assetSure.repository;

import com.example.assetSure.model.CollateralMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollateralMasterRepository extends JpaRepository<CollateralMaster, Long> {
    // Add custom queries if needed
}