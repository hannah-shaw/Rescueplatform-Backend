package com.rescueplatform_backend.controller;

import com.rescueplatform_backend.entity.Admin;
import com.rescueplatform_backend.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @program: yeb
 * @description: 和谁聊天
 * @author: Honors
 * @create: 2021-08-02 14:12
 */
@RestController
@RequestMapping("/chat")
@Api(value = "在线聊天",tags = "在线聊天")
public class ChatController {
    @Autowired
    private AdminService AdminService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/admin")
    public List<Admin> getAllAdmins(String keywords){
        //可以搜索和谁聊天
        return AdminService.getAllAdmins(keywords);
    }


}