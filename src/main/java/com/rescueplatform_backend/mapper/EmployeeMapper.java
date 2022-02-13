package com.rescueplatform_backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rescueplatform_backend.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 分页获取员工列表
     * @param page
     * @param employee
     */
    IPage<Employee> getEmployeePage(@Param("page") Page<Employee> page, @Param("employee") Employee employee,
                                    @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取员工最大编号
     * @return
     */
    String getMaxWorkID();
}
