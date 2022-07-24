package com.zzj.crm.workbench.service;

import com.zzj.crm.workbench.domain.TransactionRemark;

import java.util.List;

public interface TransactionRemarkService {
    List<TransactionRemark> queryTranRemarkForDetailByTranId(String tranId);

}
