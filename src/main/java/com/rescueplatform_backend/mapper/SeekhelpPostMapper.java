package com.rescueplatform_backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rescueplatform_backend.entity.SeekhelpPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hannah
 * @since 2022-03-07
 */
public interface SeekhelpPostMapper extends BaseMapper<SeekhelpPost> {
    /**
     * 分页获取求助列表
     * @param page
     * @param seekhelpPost
     */
    IPage<SeekhelpPost> getSeekPage(@Param("page") Page<SeekhelpPost> page, @Param("seekhelpPost") SeekhelpPost seekhelpPost);
}
