package com.zzj.crm.workbench.service.impl;

import com.zzj.crm.workbench.domain.Activity;
import com.zzj.crm.workbench.mapper.ActivityMapper;
import com.zzj.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.workbench.service.impl
 * @Author: zzj
 * @CreateTime: 2022-07-16  16:59
 * @Description: TODO
 * @Version: 1.0
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

}
