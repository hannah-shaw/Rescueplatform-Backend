package com.rescueplatform_backend.controller;


import com.rescueplatform_backend.entity.Admin;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.entity.Role;
import com.rescueplatform_backend.service.AdminService;
import com.rescueplatform_backend.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
@Api(tags = "管理员管理")
@RestController
@RequestMapping("/system/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/list")
    public List<Admin> getAllAdmins(String keywords){

        return adminService.getAllAdmins(keywords);
    }

    @ApiOperation(value = "更新操作员")
    @PutMapping("/update")
    public RespBean updateAdmin(@RequestBody Admin admin){
        if (adminService.updateById(admin)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/delete")
    public RespBean deleteAdmin(@RequestParam Integer id){
        if (adminService.removeById(id)) return RespBean.success("删除成功！");
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.list();
    }

    @ApiOperation(value = "更新操作员角色")
    @PutMapping("/updaterole")
    public RespBean updateAdminRole(@RequestParam("adminId") Integer adminId,
                                    @RequestParam("rids") List<Integer> rids){

        return adminService.updateAdminRole(adminId,rids);
    }
}
