package com.rescueplatform_backend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rescueplatform_backend.entity.Employee;
import com.rescueplatform_backend.entity.RespPageBean;
import com.rescueplatform_backend.entity.SeekhelpPost;
import com.rescueplatform_backend.mapper.EmployeeMapper;
import com.rescueplatform_backend.mapper.SeekhelpPostMapper;
import com.rescueplatform_backend.service.SeekhelpPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hannah
 * @since 2022-03-07
 */
@Service
public class SeekhelpPostServiceImpl extends ServiceImpl<SeekhelpPostMapper, SeekhelpPost> implements SeekhelpPostService {
    @Resource
    private SeekhelpPostMapper seekhelpPostMapper;

    /**
     * 分页获取求助列表
     *
     * @param currentPage 当前页
     * @param size        页面条数
     * @param seekhelpPost    操作对象
     * @return
     */
    @Override
    public RespPageBean getSeekPostList(Integer currentPage, Integer size, SeekhelpPost seekhelpPost) {
        //开启分页
        Page<SeekhelpPost> page = new Page<>(currentPage, size);
        IPage<SeekhelpPost> SeekPage = seekhelpPostMapper.getSeekPage(page, seekhelpPost);
        // SeekPage.getTotal() 总记录数， SeekPage.getRecords() 查询出来的集合(记录)
        RespPageBean respPageBean = new RespPageBean(SeekPage.getTotal(), SeekPage.getRecords());
        return respPageBean;
    }
}
