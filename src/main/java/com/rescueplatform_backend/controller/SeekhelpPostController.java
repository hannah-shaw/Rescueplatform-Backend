package com.rescueplatform_backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.rescueplatform_backend.entity.HelpPost;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.entity.RespPageBean;
import com.rescueplatform_backend.entity.SeekhelpPost;
import com.rescueplatform_backend.mapper.SeekhelpPostMapper;
import com.rescueplatform_backend.service.HelpPostService;
import com.rescueplatform_backend.service.SeekhelpPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

/**
 * <p>
 *  求助帖子管理
 * </p>
 *
 * @author hannah
 * @since 2022-03-07
 */
@Api(value = "求助帖子管理",tags = "求助帖子管理")
@RestController
@RequestMapping("/front/seekhelp-post")
public class SeekhelpPostController {
    @Autowired
    private SeekhelpPostService SeekhelpPostService;

    @ApiOperation(value = "获取所有求助信息(分页)")
    @GetMapping("/listpage")
    public RespPageBean getSeekPost(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    SeekhelpPost seekhelpPost) {
        return SeekhelpPostService.getSeekPostList(currentPage, size, seekhelpPost);

    }

    @ApiOperation(value = "获取所有求助信息")
    @GetMapping("/list")
    public List<SeekhelpPost> getAllSeekPost(){

        return SeekhelpPostService.list();
    }

    @ApiOperation(value = "获取所有求助信息数量")
    @GetMapping("/listNum")
    public int getAllSeekPostNum(){

        return SeekhelpPostService.list().size();
    }

    @ApiOperation(value = "获取求助信息省市信息")
    @GetMapping("/provinceData")
    public List<Map<String, String>> getProvinceData(){
        QueryWrapper wrapper = new QueryWrapper<>();// 构建一个查询的wrapper
        wrapper.select("distinct province");
        List<SeekhelpPost>  seekHelpPostsPro = SeekhelpPostService.list(wrapper);
        List<Map<String, String>> provinceData = new ArrayList<Map<String,String>>();
        String province = null ;
        for(int i = 0 ; i < seekHelpPostsPro.size();i++) {
            Map<String,String> provinceMap = new HashMap<String,String>();
            province = seekHelpPostsPro.get(i).getProvince();
            QueryWrapper wrapper1 = new QueryWrapper<>();
            wrapper1.like("province",province);
            int proNum = SeekhelpPostService.list(wrapper1).size();
            provinceMap.put("province",province);
            provinceMap.put("num", String.valueOf(proNum));
            provinceData.add(provinceMap);
        }

        return provinceData;
    }

    @ApiOperation(value = "根据传入id获取求助信息")
    @GetMapping("/postById")
    public SeekhelpPost getHelpPostById(@RequestParam("id") String id){
        SeekhelpPost seekhelpPost = SeekhelpPostService.getById(id);
        //查询一次访问量+1
        int viewBef = seekhelpPost.getViews();
        viewBef++;
        seekhelpPost.setViews(viewBef);
        SeekhelpPostService.updateById(seekhelpPost);
        seekhelpPost = SeekhelpPostService.getById(id);
        return seekhelpPost;
    }

    @ApiOperation(value = "添加求助信息")
    @PostMapping("/add")
    public RespBean addPosition(@RequestBody SeekhelpPost seekhelpPost){
        seekhelpPost.setCreatetime(LocalDate.now());
        if (SeekhelpPostService.save(seekhelpPost)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新求助信息")
    @PutMapping("/update")
    public RespBean updateSeekPost(@RequestBody SeekhelpPost seekhelpPost){
        if (SeekhelpPostService.updateById(seekhelpPost)){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "核实求助信息")
    @PutMapping("/check")
    public RespBean checkSeekPost(@RequestParam("id") String id){
        SeekhelpPost seekhelpPost= SeekhelpPostService.getById(id);
        seekhelpPost.setChecked(true);
        if (SeekhelpPostService.updateById(seekhelpPost)){
            return RespBean.success("核实成功!");
        }
        return RespBean.error("核实失败！");
    }

    @ApiOperation(value = "求助确认安全")
    @PutMapping("/safe")
    public RespBean safeSeekPost(@RequestParam("id") String id){
        SeekhelpPost seekhelpPost= SeekhelpPostService.getById(id);
        seekhelpPost.setSafed(true);
        if (SeekhelpPostService.updateById(seekhelpPost)){
            return RespBean.success("确认成功!");
        }
        return RespBean.error("确认失败！");
    }

    @ApiOperation(value = "取消核实求助信息")
    @PutMapping("/unCheck")
    public RespBean unCheckSeekPost(@RequestParam("id") String id){
        SeekhelpPost seekhelpPost= SeekhelpPostService.getById(id);
        seekhelpPost.setChecked(false);
        if (SeekhelpPostService.updateById(seekhelpPost)){
            return RespBean.success("取消核实成功!");
        }
        return RespBean.error("取消核实失败！");
    }

    @ApiOperation(value = "取消求助确认安全")
    @PutMapping("/unSafe")
    public RespBean unSafeSeekPost(@RequestParam("id") String id){
        SeekhelpPost seekhelpPost= SeekhelpPostService.getById(id);
        seekhelpPost.setSafed(false);
        if (SeekhelpPostService.updateById(seekhelpPost)){
            return RespBean.success("取消确认成功!");
        }
        return RespBean.error("取消确认失败！");
    }

    @ApiOperation(value = "删除求助信息")
    @DeleteMapping("/delete")
    public RespBean deletePosition(@RequestParam("id") Integer id){
        if (SeekhelpPostService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "批量删除求助信息")
    @DeleteMapping("/deletes")
    public RespBean deletePositionByIds(@RequestParam Integer[] ids){
        System.out.println("删除求助信息");
        if (SeekhelpPostService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败!");
    }
}
