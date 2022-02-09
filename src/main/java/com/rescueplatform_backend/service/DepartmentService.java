package com.rescueplatform_backend.service;

import com.rescueplatform_backend.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rescueplatform_backend.entity.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllInFo();

    /**
     * 添加部门
     * @param department
     * @return
     */
    RespBean addDept(Department department);

    /**
     * 删除部门
     * @param id
     * @return
     */
    RespBean deleteDept(Integer id);
}
