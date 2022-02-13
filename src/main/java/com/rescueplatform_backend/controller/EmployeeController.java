package com.rescueplatform_backend.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.rescueplatform_backend.entity.*;
import com.rescueplatform_backend.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
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
@RequestMapping("/employee/basic")
@Api(value = "员工管理", tags = "员工管理")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PoliticsStatusService politicsStatusService;

    @Autowired
    private JoblevelService joblevelService;

    @Autowired
    private NationService nationService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "获取所有员工(分页)")
    @GetMapping("/list")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee,
                                    @RequestParam(value = "startDate", required = false) String startDate,
                                    @RequestParam(value = "endDate", required = false) String endDate) {
        return employeeService.getEmployeeList(currentPage, size, employee, startDate, endDate);

    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/get-politics-status")
    public List<PoliticsStatus> getPoliticsStatus() {
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/get-all-job")
    public List<Joblevel> getJoblevel() {

        return joblevelService.list();
    }

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/get-all-Nation")
    public List<Nation> getNation() {

        return nationService.list();
    }

    @ApiOperation(value = "获取所有岗位")
    @GetMapping("/get-all-Position")
    public List<Position> getPosition() {

        return positionService.list();
    }

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/get-all-Department")
    public List<Department> Department() {

        return departmentService.list();
    }

    @ApiOperation(value = "获取最大工号方法一")
    @GetMapping("/get-max-WorkID")
    public RespBean getMaxWorkID1(){
        //工号为char类型 获取工号后 +1 可以保证ID自增并且唯一
        return employeeService.getMaxWorkID();
    }

    @ApiOperation(value = "获取最大工号方法二")
    @GetMapping("/get-max-WorkID2")
    public RespBean getMaxWorkID2(){
        //工号为char类型 获取工号后 +1 可以保证ID自增并且唯一
        return employeeService.getMaxWorkID2();
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/addEmp")
    public RespBean addEmp(@RequestBody Employee employee){
        RespBean bean = employeeService.addEmp(employee);
        return bean;
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/update")
    public RespBean updateEmp(@RequestBody Employee employee){
        if (employeeService.updateById(employee)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/delete/{id}")
    public RespBean deleteEmp(@PathVariable Integer id){
        if (employeeService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "导出员工表格")
    @GetMapping(value = "/export",produces = "application/octet-stream")
    public void exportEmp(HttpServletResponse response){
        employeeService.getEmployee(null,response);
    }

}
