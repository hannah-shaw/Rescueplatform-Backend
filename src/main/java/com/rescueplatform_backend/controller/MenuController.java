package com.rescueplatform_backend.controller;


import com.rescueplatform_backend.entity.Menu;
import com.rescueplatform_backend.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
@Api(value = "菜单信息",tags = "菜单信息")
@RestController
@RequestMapping("/system/cfg")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "通过用户ID获取菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenuByAdminId(){

        return menuService.getMenuByAdminId();
    }
}
