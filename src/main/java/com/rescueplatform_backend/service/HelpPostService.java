package com.rescueplatform_backend.service;

import com.rescueplatform_backend.entity.HelpPost;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.entity.RespPageBean;
import com.rescueplatform_backend.entity.SeekhelpPost;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hannah
 * @since 2022-03-08
 */
public interface HelpPostService extends IService<HelpPost> {
    /**
     * 分页获取求助列表
     * @param currentPage
     * @param size
     * @param helpPost
     * @return
     */
    RespPageBean getHelpPostList(Integer currentPage, Integer size, HelpPost helpPost);

    /**
     * 导出帮助表格
     * @param id
     */
    List<HelpPost> getHelp(Integer id);
}
