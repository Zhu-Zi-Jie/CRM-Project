package com.zzj.crm.settings.service.impl;

import com.zzj.crm.settings.domain.User;
import com.zzj.crm.settings.mapper.UserMapper;
import com.zzj.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @BelongsProject: CRM_Project
 * @BelongsPackage: com.zzj.crm.settings.service.impl
 * @Author: zzj
 * @CreateTime: 2022-07-13  16:17
 * @Description: TODO
 * @Version: 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }
}

