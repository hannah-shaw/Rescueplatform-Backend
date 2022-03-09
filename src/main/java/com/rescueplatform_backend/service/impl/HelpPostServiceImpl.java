package com.rescueplatform_backend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rescueplatform_backend.entity.HelpPost;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.entity.RespPageBean;
import com.rescueplatform_backend.mapper.HelpPostMapper;
import com.rescueplatform_backend.service.HelpPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hannah
 * @since 2022-03-08
 */
@Service
public class HelpPostServiceImpl extends ServiceImpl<HelpPostMapper, HelpPost> implements HelpPostService {
    @Resource
    private HelpPostMapper helpPostMapper;

    /**
     * 分页获取求助列表
     *
     * @param currentPage 当前页
     * @param size        页面条数
     * @param helpPost    操作对象
     * @return
     */
    @Override
    public RespPageBean getHelpPostList(Integer currentPage, Integer size, HelpPost helpPost) {
        //开启分页
        Page<HelpPost> page = new Page<>(currentPage, size);
        IPage<HelpPost> HelpPage = helpPostMapper.getHelpPage(page, helpPost);
        // SeekPage.getTotal() 总记录数， SeekPage.getRecords() 查询出来的集合(记录)
        RespPageBean respPageBean = new RespPageBean(HelpPage.getTotal(), HelpPage.getRecords());
        return respPageBean;
    }

}
