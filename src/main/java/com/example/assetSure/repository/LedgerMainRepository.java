package com.example.assetSure.repository;


import com.example.assetSure.model.LedgerMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LedgerMainRepository extends JpaRepository<LedgerMain, Long> {
    boolean existsByLenderId(Long lenderId);
}
