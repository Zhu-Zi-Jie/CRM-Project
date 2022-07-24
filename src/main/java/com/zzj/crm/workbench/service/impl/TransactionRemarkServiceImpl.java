package com.zzj.crm.workbench.service.impl;

import com.zzj.crm.workbench.domain.TransactionRemark;
import com.zzj.crm.workbench.mapper.TransactionRemarkMapper;
import com.zzj.crm.workbench.service.TransactionRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.workbench.service.impl
 * @Author: zzj
 * @CreateTime: 2022-07-24  15:39
 * @Description: TODO
 * @Version: 1.0
 */

@Service("transactionRemarkServiceImpl")
public class TransactionRemarkServiceImpl implements TransactionRemarkService {
    @Autowired
    private TransactionRemarkMapper transactionRemarkMapper;

    @Override
    public List<TransactionRemark> queryTranRemarkForDetailByTranId(String tranId) {
        return transactionRemarkMapper.selectTranRemarkForDetailByTranId(tranId);
    }
}
