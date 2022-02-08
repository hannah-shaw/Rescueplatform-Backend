package com.rescueplatform_backend.service;

import com.rescueplatform_backend.entity.MenuRole;
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
public interface MenuRoleService extends IService<MenuRole> {
    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, List<Integer> mids);
}
