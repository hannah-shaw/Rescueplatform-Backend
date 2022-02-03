package com.rescueplatform_backend.service.impl;

import com.rescueplatform_backend.entity.Admin;
import com.rescueplatform_backend.mapper.AdminMapper;
import com.rescueplatform_backend.service.AdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
