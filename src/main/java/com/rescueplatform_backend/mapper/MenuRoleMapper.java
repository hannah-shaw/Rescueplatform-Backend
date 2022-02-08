package com.rescueplatform_backend.mapper;

import com.rescueplatform_backend.entity.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 批量添加
     * @param rid
     * @param mids
     */
    Integer batchInset(@Param("rid") Integer rid, @Param("mids") List<Integer> mids);
}
