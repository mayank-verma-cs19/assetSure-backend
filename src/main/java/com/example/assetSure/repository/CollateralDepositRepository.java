package com.example.assetSure.repository;

import com.example.assetSure.model.CollateralDeposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollateralDepositRepository extends JpaRepository<CollateralDeposit, Long> {
    boolean existsByCollateralMasterId(Long collateralMasterId);
}
