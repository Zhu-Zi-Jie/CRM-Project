package com.zzj.crm.workbench.service.impl;

import com.zzj.crm.workbench.mapper.CustomerMapper;
import com.zzj.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.workbench.service.impl
 * @Author: zzj
 * @CreateTime: 2022-07-24  00:46
 * @Description: TODO
 * @Version: 1.0
 */
@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<String> queryCustomerNameByName(String name) {
        return customerMapper.selectCustomerNameByName(name);
    }
}
