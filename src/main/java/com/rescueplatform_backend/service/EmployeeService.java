package com.rescueplatform_backend.service;

import com.rescueplatform_backend.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rescueplatform_backend.entity.RespPageBean;

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
}
