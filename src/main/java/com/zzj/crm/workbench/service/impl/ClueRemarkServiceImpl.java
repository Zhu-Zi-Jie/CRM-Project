package com.zzj.crm.workbench.service.impl;

import com.zzj.crm.workbench.domain.ClueRemark;
import com.zzj.crm.workbench.mapper.ClueRemarkMapper;
import com.zzj.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.workbench.service.impl
 * @Author: zzj
 * @CreateTime: 2022-07-22  16:42
 * @Description: TODO
 * @Version: 1.0
 */
@Service("clueRemarkServiceImpl")
public class ClueRemarkServiceImpl implements ClueRemarkService {
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Override
    public List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId) {
        return clueRemarkMapper.selectClueRemarkForDetailByClueId(clueId);
    }
}
