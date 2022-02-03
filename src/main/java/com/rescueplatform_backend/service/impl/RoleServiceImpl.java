package com.rescueplatform_backend.service.impl;

import com.rescueplatform_backend.entity.Role;
import com.rescueplatform_backend.mapper.RoleMapper;
import com.rescueplatform_backend.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
