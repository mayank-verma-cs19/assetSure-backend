package com.example.assetSure.contoller;

import com.example.assetSure.dto.LedgerDTO;
import com.example.assetSure.dto.UserInfo;
import com.example.assetSure.mapper.LedgerMapper;
import com.example.assetSure.model.CollateralMaster;
import com.example.assetSure.model.LedgerMain;
import com.example.assetSure.repository.CollateralMasterRepository;
import com.example.assetSure.service.CollateralService;
import com.example.assetSure.service.LedgerMainService;
import com.example.assetSure.service.serviceImpl.LedgerServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LedgerController {

    @Autowired
    public LedgerMapper ledgerMapper;

    @Autowired
    private LedgerMainService ledgerMainService;

    @PostMapping("/addTransactions")
    public ResponseEntity<LedgerMain> addTransaction(@RequestBody LedgerDTO ledgerDTO,HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("USER_INFO");
        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        LedgerMain ledgerMain = ledgerMapper.toEntity(ledgerDTO);
        LedgerMain savedLedgerMain = ledgerMainService.save(ledgerMain,userInfo);
        return ResponseEntity.ok(savedLedgerMain);
    }



    @Autowired
    private CollateralMasterRepository collateralMasterRepository;

    @Autowired
    CollateralService collateralService;

    @PostMapping("/saveCollateralItems")
    public ResponseEntity<CollateralMaster> saveCollateralItem(
            @RequestBody CollateralMaster collateralMaster,
            HttpSession session
    ) {
        UserInfo userInfo = (UserInfo) session.getAttribute("USER_INFO");
        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        collateralMaster.setCreatedBy(userInfo.getName());
        // collateralMaster.setUserType(userInfo.getRole()); // Uncomment if needed
        collateralMaster.setCreatedById(userInfo.getId());


        CollateralMaster savedItem = collateralService.saveCollateral(collateralMaster);
        return ResponseEntity.ok(savedItem);
    }

    @GetMapping("/getCollateralItems")
    public ResponseEntity<List<CollateralMaster>> getCollateralItems(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("USER_INFO");
        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<CollateralMaster> items = collateralService.getAllCollateralItems();
        return ResponseEntity.ok(items);
    }

    @PutMapping("/updateCollateralItem/{id}")
    public ResponseEntity<CollateralMaster> updateCollateralItem(
            @PathVariable Long id,
            @RequestBody CollateralMaster updatedItem,
            HttpSession session
    ) {
        UserInfo userInfo = (UserInfo) session.getAttribute("USER_INFO");
        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CollateralMaster savedItem = collateralService.updateCollateralItem(id, updatedItem, userInfo);
        if (savedItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedItem);
    }

    @DeleteMapping("/deleteCollateralItem/{id}")
    public ResponseEntity<?> deleteCollateralItem(@PathVariable Long id, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("USER_INFO");
        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean deleted = collateralService.deleteCollateralItem(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cannot delete collateral item: It is referenced in collateral deposits or does not exist.");
        }
        return ResponseEntity.noContent().build();
    }

}
