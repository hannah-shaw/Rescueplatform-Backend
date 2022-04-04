package com.rescueplatform_backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rescueplatform_backend.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
     * 分页获取队员列表
     * @param page
     * @param employee
     */
    IPage<Employee> getEmployeePage(@Param("page") Page<Employee> page, @Param("employee") Employee employee,
                                    @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取队员最大编号
     * @return
     */
    String getMaxWorkID();

    /**
     * 导出队员表格
     * @param id
     * @return
     */
    List<Employee> getEmployeeList(@Param("id") Integer id);

    /**
     * 查询一些列添加需要的ID
     * @param employeeMap
     * @return
     */
    Employee getIdSelectNationByName(Map<String,String> employeeMap);
}
