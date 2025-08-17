package com.example.assetSure.service;

import com.example.assetSure.model.CollateralMaster;
import org.springframework.stereotype.Service;

@Service
public interface CollateralService {
    CollateralMaster saveCollateral(CollateralMaster collateralMaster);
}
