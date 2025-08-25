package com.example.assetSure.contoller;

import com.example.assetSure.model.Lender;
import com.example.assetSure.model.Settings;
import com.example.assetSure.repository.LenderRepository;
import com.example.assetSure.repository.LedgerMainRepository;
import com.example.assetSure.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SettingController {

    private final SettingsRepository settingsRepository;
    private final LenderRepository lenderRepository;
    private final LedgerMainRepository ledgerMainRepository;

    @Autowired
    public SettingController(SettingsRepository settingsRepository,
                             LenderRepository lenderRepository,
                             LedgerMainRepository ledgerMainRepository) {
        this.settingsRepository = settingsRepository;
        this.lenderRepository = lenderRepository;
        this.ledgerMainRepository = ledgerMainRepository;
    }

    // --- SETTINGS ---

    @GetMapping("/settings")
    public ResponseEntity<Settings> getSettings() {
        Optional<Settings> optSettings = settingsRepository.findById(1L);
        return optSettings.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new Settings()));
    }

    @PostMapping("/settings")
    public ResponseEntity<Settings> saveSettings(@RequestBody Settings settings) {
        settings.setId(1L);
        Settings saved = settingsRepository.save(settings);
        return ResponseEntity.ok(saved);
    }

    // --- LENDERS ---

    @GetMapping("/lenders")
    public List<Lender> getLenders() {
        return lenderRepository.findAll();
    }

    @PostMapping("/lenders")
    public ResponseEntity<Lender> addLender(@RequestBody Lender lender) {
        Lender saved = lenderRepository.save(lender);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/lenders/{id}")
    public ResponseEntity<Lender> updateLender(@PathVariable Long id, @RequestBody Lender updatedLender) {
        return lenderRepository.findById(id)
                .map(lender -> {
                    lender.setName(updatedLender.getName());
                    Lender saved = lenderRepository.save(lender);
                    return ResponseEntity.ok(saved);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/lenders/{id}")
    public ResponseEntity<?> deleteLender(@PathVariable Long id) {
        if (!lenderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        boolean isReferenced = ledgerMainRepository.existsByLenderId(id);  // check FK reference

        if (isReferenced) {
            return ResponseEntity.status(409).body("Cannot delete lender: referenced by ledger entries.");
        }

        lenderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
