package com.rescueplatform_backend.service.impl;

import com.rescueplatform_backend.entity.Admin;
import com.rescueplatform_backend.entity.Menu;
import com.rescueplatform_backend.mapper.MenuMapper;
import com.rescueplatform_backend.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    /**
     * 根据用户ID查询菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenuByAdminId() {
        return menuMapper.getMenusByAdminId(((Admin)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

    }
}
