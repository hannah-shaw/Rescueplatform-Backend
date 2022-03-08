package com.rescueplatform_backend.controller;


import com.rescueplatform_backend.entity.HelpPost;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.entity.RespPageBean;
import com.rescueplatform_backend.entity.SeekhelpPost;
import com.rescueplatform_backend.service.HelpPostService;
import com.rescueplatform_backend.service.SeekhelpPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  帮助帖子管理
 * </p>
 *
 * @author hannah
 * @since 2022-03-08
 */
@Api(value = "帮助帖子管理",tags = "帮助帖子管理")
@RestController
@RequestMapping("/front/help-post")
public class HelpPostController {
    @Autowired
    private HelpPostService HelpPostService;

    @ApiOperation(value = "获取所有帮助信息(分页)")
    @GetMapping("/listpage")
    public RespPageBean getHelpPost(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    HelpPost helpPost) {
        return HelpPostService.getHelpPostList(currentPage, size, helpPost);

    }

    @ApiOperation(value = "获取所有帮助信息")
    @GetMapping("/list")
    public List<HelpPost> getAllHelpPost(){

        return HelpPostService.list();
    }

    @ApiOperation(value = "添加求助信息")
    @PostMapping("/add")
    public RespBean addPosition(@RequestBody HelpPost helpPost){
        helpPost.setCreatetime(LocalDate.now());
        if (HelpPostService.save(helpPost)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新帮助信息")
    @PutMapping("/update")
    public RespBean updatePosition(@RequestBody HelpPost helpPost){
        if (HelpPostService.updateById(helpPost)){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除帮助信息")
    @DeleteMapping("/delete")
    public RespBean deletePosition(@RequestParam("id") Integer id){
        if (HelpPostService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "批量删除帮助信息")
    @DeleteMapping("/deletes")
    public RespBean deletePositionByIds(@RequestParam Integer[] ids){
        System.out.println("删除帮助信息");
        if (HelpPostService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败!");
    }
}
