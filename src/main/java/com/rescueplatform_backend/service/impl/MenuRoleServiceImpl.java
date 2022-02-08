package com.rescueplatform_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rescueplatform_backend.entity.MenuRole;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.mapper.MenuRoleMapper;
import com.rescueplatform_backend.service.MenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {

    @Resource
    MenuRoleMapper menuRoleMapper;


    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, List<Integer> mids) {
        //先根据角色id删除全部菜单id
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if (mids == null || mids.size() == 0){
            return RespBean.success("更新成功!");
        }
        //批量添加权限
        Integer result = menuRoleMapper.batchInset(rid, mids);
        if (result == mids.size()) return RespBean.success("修改成功！");
        return RespBean.error("修改失败！");
    }
}
