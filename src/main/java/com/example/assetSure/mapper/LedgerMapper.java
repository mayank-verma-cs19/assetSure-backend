package com.example.assetSure.mapper;

import com.example.assetSure.dto.CollateralDepositDTO;
import com.example.assetSure.dto.LedgerDTO;
import com.example.assetSure.model.CollateralDeposit;
import com.example.assetSure.model.CollateralMaster;
import com.example.assetSure.model.LedgerMain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LedgerMapper {

    public LedgerDTO toDto(LedgerMain entity) {
        if (entity == null) return null;

        LedgerDTO dto = new LedgerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setFatherName(entity.getFatherName());
        // map other fields ...

        // map collaterals
        if(entity.getCollaterals() != null){
            List<CollateralDepositDTO> collDto = entity.getCollaterals().stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
            dto.setCollaterals(collDto);
        }

        return dto;
    }

    public LedgerMain toEntity(LedgerDTO dto) {
        if (dto == null) return null;

        LedgerMain entity = new LedgerMain();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setFatherName(dto.getFatherName());
        // map other fields ...

        // map collaterals
        if(dto.getCollaterals() != null){
            List<CollateralDeposit> collEntities = dto.getCollaterals().stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
            entity.setCollaterals(collEntities);
        }

        return entity;
    }

    public CollateralDepositDTO toDto(CollateralDeposit entity) {
        if(entity == null) return null;
        CollateralDepositDTO dto = new CollateralDepositDTO();
        dto.setId(entity.getId());
        if(entity.getCollateralMaster() != null)
            dto.setCollateralMasterId(entity.getCollateralMaster().getId());
        dto.setWeight(entity.getWeight());
        dto.setNotes(entity.getNotes());
        dto.setIsReturned(entity.getIsReturned());
        return dto;
    }

    public CollateralDeposit toEntity(CollateralDepositDTO dto) {
        if(dto == null) return null;
        CollateralDeposit entity = new CollateralDeposit();
        entity.setId(dto.getId());
        CollateralMaster master = new CollateralMaster();
        master.setId(dto.getCollateralMasterId());
        entity.setCollateralMaster(master);
        entity.setWeight(dto.getWeight());
        entity.setNotes(dto.getNotes());
        entity.setIsReturned(dto.getIsReturned());
        return entity;
    }
}
