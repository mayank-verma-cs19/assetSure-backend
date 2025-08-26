package com.example.assetSure.service.serviceImpl;


import com.example.assetSure.dto.CollateralDepositDTO;
import com.example.assetSure.dto.CollateralMasterDTO;
import com.example.assetSure.dto.LedgerDTO;
import com.example.assetSure.dto.UserInfo;
import com.example.assetSure.model.CollateralDeposit;
import com.example.assetSure.model.LedgerMain;
import com.example.assetSure.repository.LedgerMainRepository;
import com.example.assetSure.service.LedgerMainService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
