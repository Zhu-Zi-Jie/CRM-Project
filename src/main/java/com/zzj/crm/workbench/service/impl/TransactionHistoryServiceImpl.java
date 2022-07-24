package com.zzj.crm.workbench.service.impl;

import com.zzj.crm.workbench.domain.TransactionHistory;
import com.zzj.crm.workbench.mapper.TransactionHistoryMapper;
import com.zzj.crm.workbench.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.workbench.service.impl
 * @Author: zzj
 * @CreateTime: 2022-07-24  15:46
 * @Description: TODO
 * @Version: 1.0
 */
@Service("transactionHistoryServiceImpl")
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
    @Autowired
    private TransactionHistoryMapper transactionHistoryMapper;

    @Override
    public List<TransactionHistory> queryTranHistoryForDetailByTranId(String tranId) {
        return transactionHistoryMapper.selectTranHistoryForDetailByTranId(tranId);
    }
}
