package com.rescueplatform_backend.service;

import com.rescueplatform_backend.entity.Employee;
import com.rescueplatform_backend.entity.RespPageBean;
import com.rescueplatform_backend.entity.SeekhelpPost;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hannah
 * @since 2022-03-07
 */
public interface SeekhelpPostService extends IService<SeekhelpPost> {
    /**
     * 分页获取求助列表
     * @param currentPage
     * @param size
     * @param seekhelpPost
     * @return
     */
    RespPageBean getSeekPostList(Integer currentPage, Integer size, SeekhelpPost seekhelpPost);
}
