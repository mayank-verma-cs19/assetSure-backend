package com.example.assetSure.service;

import com.example.assetSure.model.CollateralMaster;
import com.example.assetSure.repository.CollateralMasterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CollateralServiceImpl implements CollateralService {

    private final CollateralMasterRepository collateralMasterRepository;

    // Constructor injection recommended
    public CollateralServiceImpl(CollateralMasterRepository collateralMasterRepository) {
        this.collateralMasterRepository = collateralMasterRepository;
    }

    @Override
    @Transactional
    public CollateralMaster saveCollateral(CollateralMaster collateralMaster) {
        return collateralMasterRepository.save(collateralMaster);
    }
}
