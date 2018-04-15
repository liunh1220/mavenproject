package com.example.common.util;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AppServerUtil {
    public static String getUserDir() {
        // oracle 
        String userDir = System.getProperty("oracle.j2ee.home");
        if (!StringUtils.isEmpty(userDir)) {
            return userDir + File.separator + "projectName";
        }
        
        // 获取user.dir目录
        userDir = System.getProperty("user.dir");
        
        return userDir;
    }
}
