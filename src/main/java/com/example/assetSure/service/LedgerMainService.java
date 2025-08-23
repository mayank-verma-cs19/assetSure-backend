package com.example.assetSure.service;

import com.example.assetSure.dto.UserInfo;
import com.example.assetSure.model.LedgerMain;
import org.springframework.stereotype.Service;

@Service
public interface LedgerMainService {

    LedgerMain save(LedgerMain ledgerMain, UserInfo userInfo);
}
