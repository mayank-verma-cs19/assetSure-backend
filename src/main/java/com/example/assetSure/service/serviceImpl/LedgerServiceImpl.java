package com.example.assetSure.service.serviceImpl;


import com.example.assetSure.dto.UserInfo;
import com.example.assetSure.model.CollateralDeposit;
import com.example.assetSure.model.LedgerMain;
import com.example.assetSure.repository.LedgerMainRepository;
import com.example.assetSure.service.LedgerMainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class LedgerServiceImpl implements LedgerMainService {

    private final LedgerMainRepository ledgerMainRepository;

    public LedgerServiceImpl(LedgerMainRepository ledgerMainRepository) {
        this.ledgerMainRepository = ledgerMainRepository;
    }

    @Transactional
    public LedgerMain save(LedgerMain ledgerMain, UserInfo userInfo) {
        // Set default values for LedgerMain
        if (ledgerMain.getCreatedOn() == null) ledgerMain.setCreatedOn(LocalDateTime.now());
        if (ledgerMain.getIsDeleted() == null) ledgerMain.setIsDeleted(false);
        ledgerMain.setCreatedBy(userInfo.getId().toString());

        LedgerMain savedLedgerMain = ledgerMainRepository.save(ledgerMain);

        if (savedLedgerMain.getCollaterals() != null) {
            for (CollateralDeposit collateral : savedLedgerMain.getCollaterals()) {
                collateral.setLedger(savedLedgerMain);           // foreign key
                if (collateral.getCreatedOn() == null) collateral.setCreatedOn(LocalDateTime.now());
                if (collateral.getIsDeleted() == null) collateral.setIsDeleted(false);
                collateral.setCreatedBy(userInfo.getUsername());
            }
        }

        // Save ledger again to cascade collateral deposits
        return ledgerMainRepository.save(savedLedgerMain);
    }
}
