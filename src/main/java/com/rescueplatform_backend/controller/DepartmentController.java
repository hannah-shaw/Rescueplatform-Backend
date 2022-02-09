package com.rescueplatform_backend.controller;


import com.rescueplatform_backend.entity.Department;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.service.DepartmentService;
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
@RestController
@RequestMapping("/system/basic/department")
@Api(value = "部门管理", tags = "部门管理")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @ApiOperation("获取所有部门")
    @GetMapping("/list")
    public List<Department> getAllInFo(){

        return departmentService.getAllInFo();
    }

    @ApiOperation("添加部门")
    @PostMapping("/add")
    public RespBean addDept(@RequestBody Department department){

        return departmentService.addDept(department);
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/delete")
    public RespBean deleteDept(@RequestParam("id") Integer id){
        return departmentService.deleteDept(id);
    }
}
