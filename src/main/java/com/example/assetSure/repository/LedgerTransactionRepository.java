package com.example.assetSure.repository;

import com.example.assetSure.model.LedgerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerTransactionRepository extends JpaRepository<LedgerTransaction, Long> {

    // You can add custom queries if needed
    // Example: List<LedgerTransaction> findByLedgerMainId(Long ledgerId);
}
