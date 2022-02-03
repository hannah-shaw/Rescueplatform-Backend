package com.rescueplatform_backend.service.impl;

import com.rescueplatform_backend.entity.Employee;
import com.rescueplatform_backend.mapper.EmployeeMapper;
import com.rescueplatform_backend.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
