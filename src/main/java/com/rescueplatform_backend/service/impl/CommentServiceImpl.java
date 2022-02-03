package com.rescueplatform_backend.service.impl;

import com.rescueplatform_backend.entity.Comment;
import com.rescueplatform_backend.mapper.CommentMapper;
import com.rescueplatform_backend.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
