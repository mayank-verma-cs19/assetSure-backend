package com.example.assetSure.service;


import com.example.assetSure.model.LedgerMain;
import com.example.assetSure.repository.LedgerMainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LedgerService {

    private final LedgerMainRepository ledgerMainRepository;

    public LedgerService(LedgerMainRepository ledgerMainRepository) {
        this.ledgerMainRepository = ledgerMainRepository;
    }

    @Transactional
    public LedgerMain save(LedgerMain ledgerMain) {
        // Ensures cascade save of collateral deposits if properly mapped in entity
        return ledgerMainRepository.save(ledgerMain);
    }

    // You can add more service methods such as findById, findAll, delete etc.
}
