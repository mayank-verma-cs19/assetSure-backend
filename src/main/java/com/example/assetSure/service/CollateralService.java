package com.example.assetSure.service;

import com.example.assetSure.model.CollateralMaster;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollateralService {
    CollateralMaster saveCollateral(CollateralMaster collateralMaster);
    List<CollateralMaster> getAllCollateralItems();
}
