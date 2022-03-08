package com.rescueplatform_backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rescueplatform_backend.entity.HelpPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rescueplatform_backend.entity.SeekhelpPost;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hannah
 * @since 2022-03-08
 */
public interface HelpPostMapper extends BaseMapper<HelpPost> {
    /**
     * 分页获取帮助列表
     * @param page
     * @param helpPost
     */
    IPage<HelpPost> getHelpPage(@Param("page") Page<HelpPost> page, @Param("helpPost") HelpPost helpPost);
}
