package com.example.assetSure.mapper;

import com.example.assetSure.dto.CollateralDepositDTO;
import com.example.assetSure.dto.LedgerDTO;
import com.example.assetSure.model.CollateralDeposit;
import com.example.assetSure.model.CollateralMaster;
import com.example.assetSure.model.LedgerMain;
import com.example.assetSure.repository.CollateralMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LedgerMapper {

    @Autowired
    private CollateralMasterRepository collateralMasterRepository;

    // LedgerMain entity -> DTO
    public LedgerDTO toDto(LedgerMain entity) {
        if (entity == null) return null;

        LedgerDTO dto = new LedgerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setFatherName(entity.getFatherName());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setAmount(entity.getAmount());
        dto.setEstimateDays(entity.getEstimateDays());
        dto.setRoi(entity.getRoi());
        dto.setDate(entity.getDate());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setRepaymentAmount(entity.getRepaymentAmount());
        dto.setInterestCharged(entity.getInterestCharged());
        dto.setClosedByName(entity.getClosedByName());
        dto.setClosedByContact(entity.getClosedByContact());
        dto.setFinalComments(entity.getFinalComments());

        if (entity.getCollaterals() != null) {
            List<CollateralDepositDTO> collDto = entity.getCollaterals().stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
            dto.setCollaterals(collDto);
        }

        return dto;
    }

    // Ledger DTO -> Entity
    public LedgerMain toEntity(LedgerDTO dto) {
        if (dto == null) return null;

        LedgerMain entity = new LedgerMain();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setFatherName(dto.getFatherName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setAmount(dto.getAmount());
        entity.setEstimateDays(dto.getEstimateDays());
        entity.setRoi(dto.getRoi());
        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setRepaymentAmount(dto.getRepaymentAmount());
        entity.setInterestCharged(dto.getInterestCharged());
        entity.setClosedByName(dto.getClosedByName());
        entity.setClosedByContact(dto.getClosedByContact());
        entity.setFinalComments(dto.getFinalComments());

        if (dto.getCollaterals() != null) {
            List<CollateralDeposit> collEntities = dto.getCollaterals().stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());

            // Set back reference for each collateral deposit
            for (CollateralDeposit deposit : collEntities) {
                deposit.setLedger(entity);
            }

            entity.setCollaterals(collEntities);
        }

        return entity;
    }

    // CollateralDeposit entity -> DTO
    public CollateralDepositDTO toDto(CollateralDeposit entity) {
        if (entity == null) return null;

        CollateralDepositDTO dto = new CollateralDepositDTO();
        dto.setId(entity.getId());

        if (entity.getCollateralMaster() != null) {
            dto.setCollateralMasterId(entity.getCollateralMaster().getId());
            dto.setCollateralMasterName(entity.getCollateralMaster().getName());
        }

        dto.setWeight(entity.getWeight());
        dto.setNotes(entity.getNotes());
        dto.setIsReturned(entity.getIsReturned());
        return dto;
    }

    // CollateralDeposit DTO -> Entity (fetch managed CollateralMaster from DB)
    public CollateralDeposit toEntity(CollateralDepositDTO dto) {
        if (dto == null) return null;

        CollateralDeposit entity = new CollateralDeposit();
        entity.setId(dto.getId());

        if (dto.getCollateralMasterId() != null) {
            CollateralMaster master = collateralMasterRepository.findById(dto.getCollateralMasterId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Invalid collateralMasterId: " + dto.getCollateralMasterId()
                    ));
            entity.setCollateralMaster(master);
        } else {
            entity.setCollateralMaster(null);
        }

        entity.setWeight(dto.getWeight());
        entity.setNotes(dto.getNotes());
        entity.setIsReturned(dto.getIsReturned());

        return entity;
    }
}
