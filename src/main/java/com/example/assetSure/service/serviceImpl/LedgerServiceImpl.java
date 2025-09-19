package com.example.assetSure.service.serviceImpl;


import com.example.assetSure.dto.CollateralDepositDTO;
import com.example.assetSure.dto.CollateralMasterDTO;
import com.example.assetSure.dto.LedgerDTO;
import com.example.assetSure.dto.UserInfo;
import com.example.assetSure.model.CollateralDeposit;
import com.example.assetSure.model.LedgerMain;
import com.example.assetSure.model.LedgerTransaction;
import com.example.assetSure.model.Lender;
import com.example.assetSure.repository.LedgerMainRepository;
import com.example.assetSure.repository.LedgerTransactionRepository;
import com.example.assetSure.repository.LenderRepository;
import com.example.assetSure.service.LedgerMainService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class LedgerServiceImpl implements LedgerMainService {

    private final LedgerMainRepository ledgerMainRepository;

    public LedgerServiceImpl(LedgerMainRepository ledgerMainRepository) {
        this.ledgerMainRepository = ledgerMainRepository;
    }

    @Autowired
    private LedgerTransactionRepository ledgerTransactionRepository;

    @Transactional
    public LedgerMain save(LedgerMain ledgerMain, UserInfo userInfo) {
        if (ledgerMain.getId() != null) {
            // Existing ledger - Update flow
            LedgerMain existing = ledgerMainRepository.findById(ledgerMain.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Ledger not found with id " + ledgerMain.getId()));

            // Update fields - selectively or all needed
            existing.setName(ledgerMain.getName());
            existing.setFatherName(ledgerMain.getFatherName());
            existing.setPhone(ledgerMain.getPhone());
            existing.setAmount(ledgerMain.getAmount());
            existing.setEstimateDays(ledgerMain.getEstimateDays());
            existing.setRoi(ledgerMain.getRoi());
            existing.setDate(ledgerMain.getDate());
            existing.setDescription(ledgerMain.getDescription());
            existing.setLender(ledgerMain.getLender());
            existing.setAddress(ledgerMain.getAddress());

            // Handle collaterals - remove old, add new or update associations accordingly
            existing.getCollaterals().clear();

            if (ledgerMain.getCollaterals() != null) {
                for (CollateralDeposit collateral : ledgerMain.getCollaterals()) {
                    collateral.setLedger(existing); // set parent ref
                    if (collateral.getCreatedOn() == null) collateral.setCreatedOn(LocalDateTime.now());
                    if (collateral.getIsDeleted() == null) collateral.setIsDeleted(false);
                    collateral.setCreatedBy(userInfo.getUsername());
                    existing.getCollaterals().add(collateral);
                }
            }

            // Update audit fields
            existing.setUpdatedBy(userInfo.getUsername());
            existing.setUpdatedOn(LocalDateTime.now());

            // Save updated entity (cascade persists collaterals)
            return ledgerMainRepository.save(existing);

        } else {
            // New ledger - Insert flow (existing logic unchanged)
            if (ledgerMain.getCreatedOn() == null) ledgerMain.setCreatedOn(LocalDateTime.now());
            if (ledgerMain.getIsDeleted() == null) ledgerMain.setIsDeleted(false);
            ledgerMain.setCreatedBy(userInfo.getId().toString());

            if (ledgerMain.getCollaterals() != null) {
                for (CollateralDeposit collateral : ledgerMain.getCollaterals()) {
                    collateral.setLedger(ledgerMain);
                    if (collateral.getCreatedOn() == null) collateral.setCreatedOn(LocalDateTime.now());
                    if (collateral.getIsDeleted() == null) collateral.setIsDeleted(false);
                    collateral.setCreatedBy(userInfo.getUsername());
                }
            }

            LedgerMain savedLedgerMain = ledgerMainRepository.save(ledgerMain);

            LedgerTransaction transaction = new LedgerTransaction();
            transaction.setLedgerMain(savedLedgerMain);
            transaction.setTransactionType(LedgerTransaction.TransactionType.DEBIT);
            transaction.setAmount(BigDecimal.valueOf(Double.parseDouble(savedLedgerMain.getAmount().toString())));
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setComments("Initial loan entry from LedgerMain");
            transaction.setCreatedBy(userInfo.getUsername());
            transaction.setCreatedOn(LocalDateTime.now());
            transaction.setIsDeleted(false);

            ledgerTransactionRepository.save(transaction);

            return savedLedgerMain;
        }
    }



    @Override
    public List<LedgerDTO> getAllLedgers(UserInfo userInfo) {
        List<LedgerMain> ledgers = ledgerMainRepository.findAll(
                Sort.by(Sort.Direction.DESC, "createdOn") // or "createdOn"
        );

        return ledgers.stream().map(ledger -> {
            LedgerDTO dto = new LedgerDTO();
            dto.setId(ledger.getId());
            dto.setName(ledger.getName());
            dto.setFatherName(ledger.getFatherName());
            dto.setAddress(ledger.getAddress());
            dto.setPhone(ledger.getPhone());
            dto.setAmount(ledger.getAmount());
            dto.setEstimateDays(ledger.getEstimateDays());
            dto.setRoi(ledger.getRoi());
            dto.setDate(ledger.getDate());
            dto.setDescription(ledger.getDescription());
            dto.setStatus(ledger.getStatus());
            dto.setRepaymentAmount(ledger.getRepaymentAmount());
            dto.setInterestCharged(ledger.getInterestCharged());
            dto.setClosedByName(ledger.getClosedByName());
            dto.setClosedByContact(ledger.getClosedByContact());
            dto.setFinalComments(ledger.getFinalComments());
            if (ledger.getLender() != null) {
                dto.setLenderName(ledger.getLender().getName());
            }else {
                dto.setLenderName("");
            }

            // Map collaterals
            List<CollateralDepositDTO> depositDTOs = ledger.getCollaterals().stream().map(deposit -> {
                CollateralDepositDTO depDTO = new CollateralDepositDTO();
                depDTO.setId(deposit.getId());
                depDTO.setCollateralMasterId(
                        deposit.getCollateralMaster() != null ? deposit.getCollateralMaster().getId() : null
                );
                depDTO.setCollateralMasterName(deposit.getCollateralMaster().getName());
                depDTO.setWeight(deposit.getWeight());
                depDTO.setNotes(deposit.getNotes());
                depDTO.setIsReturned(deposit.getIsReturned());
                return depDTO;
            }).toList();

            dto.setCollaterals(depositDTOs);

            return dto;
        }).toList();
    }

}
