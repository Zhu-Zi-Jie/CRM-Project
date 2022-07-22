package com.zzj.crm.settings.service.impl;

import com.zzj.crm.settings.domain.DicValue;
import com.zzj.crm.settings.mapper.DicValueMapper;
import com.zzj.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.settings.service.impl
 * @Author: zzj
 * @CreateTime: 2022-07-22  15:12
 * @Description: TODO
 * @Version: 1.0
 */
@Service("dicValueServiceImpl")
public class DicValueServiceImpl implements DicValueService {
    @Autowired
    private DicValueMapper dicValueMapper;
    @Override
    public List<DicValue> queryDicValueByTypeCode(String typeCode) {
        return dicValueMapper.selectDicValueByTypeCode(typeCode);
    }
}
