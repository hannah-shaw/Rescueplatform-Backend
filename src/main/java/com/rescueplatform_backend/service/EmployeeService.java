package com.rescueplatform_backend.service;

import com.rescueplatform_backend.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.entity.RespPageBean;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
public interface EmployeeService extends IService<Employee> {
    /**
     * 分页获取员工列表
     * @param currentPage
     * @param size
     * @param employee
     * @return
     */
    RespPageBean getEmployeeList(Integer currentPage, Integer size, Employee employee, String startDate, String endDate);

    /**
     * 获取工号
     * @return
     */
    RespBean getMaxWorkID();

    /**
     * 获取工号 方法2
     * @return
     */
    RespBean getMaxWorkID2();

    /**
     * 添加员工
     * @param employee
     */
    RespBean addEmp(Employee employee);

    /**
     * 导出员工表格
     * @param id
     */
    void getEmployee(Integer id, HttpServletResponse response);
}
