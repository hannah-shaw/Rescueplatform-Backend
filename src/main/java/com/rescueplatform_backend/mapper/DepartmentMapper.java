package com.rescueplatform_backend.mapper;

import com.rescueplatform_backend.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllInFo(Integer parentId);

    /**
     * 添加部门
     * @param department
     * @return
     */
    void addDept(Department department);

    /**
     * 删除部门
     * @param department
     * @return
     */
    void deleteDept(Department department);
}
