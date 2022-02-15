package com.rescueplatform_backend.service;

import com.rescueplatform_backend.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
public interface AdminService extends IService<Admin> {
    /**
     * 登录之后返回token
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);


    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmins(String keywords);

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    RespBean updateAdminRole(@Param("adminId") Integer adminId, @Param("rids") List<Integer> rids);

    /**
     * 个人中心修改用户密码
     * @param oldPass
     * @param pass
     * @param adminId
     * @return
     */
    RespBean updateAdminPassWord(String oldPass, String pass, Integer adminId);

}
