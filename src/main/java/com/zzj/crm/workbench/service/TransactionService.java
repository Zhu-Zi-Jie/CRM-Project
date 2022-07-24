package com.zzj.crm.workbench.service;

import com.zzj.crm.workbench.domain.FunnelVO;
import com.zzj.crm.workbench.domain.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    void saveCreateTran(Map<String,Object> map);

    Transaction queryTranForDetailById(String id);

    List<FunnelVO> queryCountOfTranGroupByStage();
}
