package com.example.assetSure.service;

import com.example.assetSure.dto.UserInfo;
import com.example.assetSure.model.CollateralMaster;
import com.example.assetSure.repository.CollateralDepositRepository;
import com.example.assetSure.repository.CollateralMasterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<CollateralMaster> getAllCollateralItems() {
        return collateralMasterRepository.findAll();
    }

        @Override
        public CollateralMaster updateCollateralItem(Long id, CollateralMaster updatedItem, UserInfo userInfo) {
            Optional<CollateralMaster> existingOpt = collateralMasterRepository.findById(id);
            if (existingOpt.isEmpty()) return null;

            CollateralMaster existing = existingOpt.get();

    //        existing.setName(updatedItem.getName());
    //        existing.setMaterial(updatedItem.getMaterial());
    //        existing.setPurity(updatedItem.getPurity());
    //        existing.setEstWeight(updatedItem.getEstWeight());
            existing.setNotes(updatedItem.getNotes());
            existing.setIsActive(updatedItem.getIsActive());
            existing.setUpdatedBy(userInfo.getName());
            existing.setUpdatedOn(userInfo.getName());

            return collateralMasterRepository.save(existing);
        }


    @Autowired
    private CollateralDepositRepository collateralDepositRepository;

    @Override
    public boolean deleteCollateralItem(Long id) {
        // Check if this collateral is referenced in CollateralDeposit
        boolean isMapped = collateralDepositRepository.existsByCollateralMasterId(id);
        if (isMapped) {
            // Cannot delete: mapped somewhere else
            return false;
        }
        if (!collateralMasterRepository.existsById(id)) {
            return false;
        }
        collateralMasterRepository.deleteById(id);
        return true;
    }

}
