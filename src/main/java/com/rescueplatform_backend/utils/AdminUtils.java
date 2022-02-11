package com.rescueplatform_backend.utils;

import com.rescueplatform_backend.entity.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @description: 操作员工具类
 * @author: hannah
 */
public class AdminUtils {

    /**
     * 获取当前登录操作员
     * @return
     */
    public static Admin getCurrentAdmin(){
        return (Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}