package com.rescueplatform_backend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rescueplatform_backend.entity.Employee;
import com.rescueplatform_backend.entity.RespPageBean;
import com.rescueplatform_backend.mapper.EmployeeMapper;
import com.rescueplatform_backend.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 分页获取员工列表
     *
     * @param currentPage 当前页
     * @param size        页面条数
     * @param employee    操作对象
     * @return
     */
    @Override
    public RespPageBean getEmployeeList(Integer currentPage, Integer size, Employee employee, String startDate, String endDate) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeePage = employeeMapper.getEmployeePage(page, employee, startDate, endDate);
        // employeePage.getTotal() 总记录数， employeePage.getRecords() 查询出来的集合(记录)
        RespPageBean respPageBean = new RespPageBean(employeePage.getTotal(), employeePage.getRecords());
        return respPageBean;
    }
}
