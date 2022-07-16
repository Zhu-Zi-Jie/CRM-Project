package com.zzj.crm.commons.utils;

import java.util.UUID;

/**
 * @BelongsProject: CRM-Project
 * @BelongsPackage: com.zzj.crm.commons.utils
 * @Author: zzj
 * @CreateTime: 2022-07-16  17:16
 * @Description: 获取处理后的uuid字符串
 * @Version: 1.0
 */
public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
