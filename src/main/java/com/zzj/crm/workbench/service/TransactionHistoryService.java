package com.zzj.crm.workbench.service;

import com.zzj.crm.workbench.domain.TransactionHistory;

import java.util.List;

public interface TransactionHistoryService {
    List<TransactionHistory> queryTranHistoryForDetailByTranId(String tranId);

}
