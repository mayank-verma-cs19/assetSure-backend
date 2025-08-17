package com.example.assetSure.contoller;

import com.example.assetSure.dto.UserInfo;
import com.example.assetSure.model.CollateralMaster;
import com.example.assetSure.model.LedgerMain;
import com.example.assetSure.model.User;
import com.example.assetSure.repository.CollateralMasterRepository;
import com.example.assetSure.service.CollateralService;
import com.example.assetSure.service.LedgerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LedgerController {

    private final LedgerService ledgerService;

    @Autowired
    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @PostMapping("/addTransactions")
    public ResponseEntity<LedgerMain> addTransaction(@RequestBody LedgerMain ledgerMain) {
        // Set bidirectional relationships
        if (ledgerMain.getCollaterals() != null) {
            ledgerMain.getCollaterals().forEach(collateral -> collateral.setLedgerMain(ledgerMain));
        }

        LedgerMain savedEntry = ledgerService.save(ledgerMain);
        return ResponseEntity.ok(savedEntry);
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
        // Retrieve user info from session
        UserInfo userInfo = (UserInfo) session.getAttribute("USER_INFO");
        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Use userInfo fields as needed
        collateralMaster.setCreatedBy(userInfo.getName());
        // collateralMaster.setUserType(userInfo.getRole()); // Uncomment if needed

        CollateralMaster savedItem = collateralService.saveCollateral(collateralMaster);
        return ResponseEntity.ok(savedItem);
    }

    // Other controller methods...
}
