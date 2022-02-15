package com.rescueplatform_backend.controller;

import com.rescueplatform_backend.entity.Admin;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @description: 用户个人中心
 * @author: hannah
 */
@RestController
@Api(value = "个人中心",tags = "个人中心")
public class AdminInfoController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "更新当前用户信息")
    @PutMapping("/admin-info")
    // Authentication 这个对象是 Security 提供的可以用他直接获取到当前登录用户的信息，Admin 是前端用户传过来的用户信息
    public RespBean updateAdmin(@RequestBody Admin admin, Authentication authentication){
        if (adminService.updateById(admin)){//只是跟新了数据库
            //更新用户的全局上下文信息
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(admin, null, authentication.getAuthorities()));
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation(value = "更新用户密码")
    @PutMapping("/admin-pass")
    public RespBean updateAdminPassWord(@RequestBody Map<String,Object> info){
        //旧密码
        String oldPass = (String) info.get("oldPass");
        //新密码
        String pass = (String) info.get("pass");
        //当前登录用户ID
        Integer adminId = (Integer) info.get("adminId");
        return adminService.updateAdminPassWord(oldPass,pass,adminId);
    }

}