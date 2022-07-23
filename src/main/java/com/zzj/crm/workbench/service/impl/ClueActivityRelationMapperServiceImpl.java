package com.zzj.crm.workbench.service.impl;

import com.zzj.crm.workbench.domain.ClueActivityRelation;
import com.zzj.crm.workbench.mapper.ClueActivityRelationMapper;
import com.zzj.crm.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.workbench.service.impl
 * @Author: zzj
 * @CreateTime: 2022-07-22  22:05
 * @Description: TODO
 * @Version: 1.0
 */
@Service("clueActivityRelationServiceImpl")
public class ClueActivityRelationMapperServiceImpl implements ClueActivityRelationService {
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Override
    public int saveCreateClueActivityRelationByList(List<ClueActivityRelation> list) {
        return clueActivityRelationMapper.insertClueActivityRelationByList(list);
    }

    @Override
    public int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation relation) {
        return clueActivityRelationMapper.deleteClueActivityRelationByClueIdActivityId(relation);
    }
}
