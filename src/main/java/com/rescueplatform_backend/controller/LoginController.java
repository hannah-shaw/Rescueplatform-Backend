package com.rescueplatform_backend.controller;

import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.service.AdminService;
import com.rescueplatform_backend.entity.Admin;
import com.rescueplatform_backend.entity.AdminLoginParam;
import com.rescueplatform_backend.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 登录
 */
@Api(tags = "用户登录")
@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){

        //登录验证
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword()
                ,adminLoginParam.getCaptcha()
                ,request);
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/admin/info")
    public Admin getUserInfo(Principal principal){
        if (principal == null){
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功！");
    }

}
