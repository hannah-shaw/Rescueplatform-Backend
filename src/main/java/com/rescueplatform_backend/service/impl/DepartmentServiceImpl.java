package com.rescueplatform_backend.service.impl;

import com.rescueplatform_backend.entity.Department;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.mapper.DepartmentMapper;
import com.rescueplatform_backend.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Resource
    DepartmentMapper departmentMapper;

    /**
     * 获取所有部门
     * @return
     */
    @Override
    public List<Department> getAllInFo() {
        return departmentMapper.getAllInFo(-1);
    }

    /**
     * 添加部门
     * @param department
     * @return
     */
    @Override
    public RespBean addDept(Department department) {
        department.setEnabled(true);
        departmentMapper.addDept(department);
        if (department.getResult() == 1){
            return RespBean.success("添加成功！",department);
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public RespBean deleteDept(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentMapper.deleteDept(department);
        if (department.getResult() == -2) return RespBean.error("该部门下还有子部门，删除失败！");
        if (department.getResult() == -1) return RespBean.error("该部门下还有队员，删除失败！");
        if (department.getResult() == 1) return RespBean.success("删除成功！");
        return RespBean.error("删除失败！");
    }
}
