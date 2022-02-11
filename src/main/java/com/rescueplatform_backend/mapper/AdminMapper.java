package com.rescueplatform_backend.mapper;

import com.rescueplatform_backend.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);

    /**
     * 获取管理员
     * @param username
     */
    Admin getAdmin(@Param("username") String username);
}
