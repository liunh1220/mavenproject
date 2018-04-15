package com.ssm.util;

import java.io.File;

import org.apache.commons.lang.StringUtils;

public class AppServerUtil {
    public static String getUserDir() {
        // oracle 
        String userDir = System.getProperty("oracle.j2ee.home");
        if (!StringUtils.isEmpty(userDir)) {
            return userDir + File.separator + "test";
        }
        
        // 获取user.dir目录
        userDir = System.getProperty("user.dir");
        
        return userDir;
    }
}
