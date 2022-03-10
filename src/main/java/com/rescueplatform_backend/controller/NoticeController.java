package com.rescueplatform_backend.controller;


import com.rescueplatform_backend.entity.Notice;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  通知管理
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
@Api(value = "通知管理",tags = "通知管理")
@RestController
@RequestMapping("/front/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @ApiOperation(value = "获取所有通知信息")
    @GetMapping("/list")
    public List<Notice> getAllNotice(){
        return noticeService.list();
    }

    @ApiOperation(value = "获取最新通知信息")
    @GetMapping("/newNotice")
    public Notice getNewNotice(){
        List<Notice> list = noticeService.list();
        return list.get(list.size() - 1);
    }

    @ApiOperation(value = "添加通知信息")
    @PostMapping("/add")
    public RespBean addPosition(@RequestBody Notice notice){
        notice.setCreatetime(LocalDateTime.now());
        if (noticeService.save(notice)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新通知信息")
    @PutMapping("/update")
    public RespBean updatePosition(@RequestBody Notice notice){
        if (noticeService.updateById(notice)){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除通知信息")
    @DeleteMapping("/delete")
    public RespBean deletePosition(@RequestParam("id") Integer id){
        if (noticeService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "批量删除通知信息")
    @DeleteMapping("/deletes")
    public RespBean deletePositionByIds(@RequestParam Integer[] ids){
        System.out.println("删除岗位信息");
        if (noticeService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败!");
    }
}
