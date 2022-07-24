package com.zzj.crm.workbench.service.impl;

import com.zzj.crm.commons.constant.Constant;
import com.zzj.crm.commons.utils.DateUtils;
import com.zzj.crm.commons.utils.UUIDUtils;
import com.zzj.crm.settings.domain.User;
import com.zzj.crm.workbench.domain.Customer;
import com.zzj.crm.workbench.domain.FunnelVO;
import com.zzj.crm.workbench.domain.Transaction;
import com.zzj.crm.workbench.domain.TransactionHistory;
import com.zzj.crm.workbench.mapper.CustomerMapper;
import com.zzj.crm.workbench.mapper.TransactionHistoryMapper;
import com.zzj.crm.workbench.mapper.TransactionMapper;
import com.zzj.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.workbench.service.impl
 * @Author: zzj
 * @CreateTime: 2022-07-24  01:01
 * @Description: TODO
 * @Version: 1.0
 */
@Service("transactionServiceImpl")
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionHistoryMapper transactionHistoryMapper;


    @Override
    public void saveCreateTran(Map<String, Object> map) {
        String customerName=(String) map.get("customerName");
        User user=(User) map.get(Constant.SESSION_USER);
        //根据name精确查询客户
        Customer customer=customerMapper.selectCustomerByName(customerName);
        //如果客户不存在，则新建客户
        if(customer==null){
            customer=new Customer();
            customer.setOwner(user.getId());
            customer.setName(customerName);
            customer.setId(UUIDUtils.getUUID());
            customer.setCreateTime(DateUtils.formateDateTime(new Date()));
            customer.setCreateBy(user.getId());
            customerMapper.insertCustomer(customer);
        }
        //保存创建的交易
        Transaction tran=new Transaction();
        tran.setStage((String) map.get("stage"));
        tran.setOwner((String) map.get("owner"));
        tran.setNextContactTime((String) map.get("nextContactTime"));
        tran.setName((String) map.get("name"));
        tran.setMoney((String) map.get("money"));
        tran.setId(UUIDUtils.getUUID());
        tran.setExpectedDate((String) map.get("expectedDate"));
        tran.setCustomerId(customer.getId());
        tran.setCreateTime(DateUtils.formateDateTime(new Date()));
        tran.setCreateBy(user.getId());
        tran.setContactSummary((String) map.get("contactSummary"));
        tran.setContactsId((String) map.get("contactsId"));
        tran.setActivityId((String) map.get("activityId"));
        tran.setDescription((String) map.get("description"));
        tran.setSource((String) map.get("source"));
        tran.setType((String) map.get("type"));
        transactionMapper.insertTran(tran);
        //保存交易历史
        TransactionHistory tranHistory=new TransactionHistory();
        tranHistory.setCreateBy(user.getId());
        tranHistory.setCreateTime(DateUtils.formateDateTime(new Date()));
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setId(UUIDUtils.getUUID());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setStage(tran.getStage());
        tranHistory.setTranId(tran.getId());
        transactionHistoryMapper.insertTranHistory(tranHistory);
    }

    @Override
    public Transaction queryTranForDetailById(String id) {
        return transactionMapper.selectTranForDetailById(id);
    }

    @Override
    public List<FunnelVO> queryCountOfTranGroupByStage() {
        return transactionMapper.selectCountOfTranGroupByStage();
    }
}
