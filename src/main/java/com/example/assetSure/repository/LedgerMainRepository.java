package com.example.assetSure.repository;


import com.example.assetSure.model.LedgerMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerMainRepository extends JpaRepository<LedgerMain, Long> {
    // Additional custom query methods if needed
    boolean existsByLenderId(Long lenderId);

}
