package com.example.assetSure.repository;

import com.example.assetSure.model.Lender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LenderRepository extends JpaRepository<Lender, Long> {
    // Additional custom queries (if any) can be added here
}
