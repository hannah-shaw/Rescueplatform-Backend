package com.rescueplatform_backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import java.util.*;

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

    @ApiOperation(value = "获取所有帮助信息数量")
    @GetMapping("/listNum")
    public int getAllHelpPostNum(){

        return HelpPostService.list().size();
    }

    @ApiOperation(value = "获取帮助信息省信息")
    @GetMapping("/provinceData")
    public List<Map<String, String>> getProvinceData(){
        QueryWrapper wrapper = new QueryWrapper<>();// 构建一个查询的wrapper
        wrapper.select("distinct province");
        List<HelpPost>  HelpPostsPro = HelpPostService.list(wrapper);
        List<Map<String, String>> provinceData = new ArrayList<Map<String,String>>();
        String province = null ;
        for(int i = 0 ; i < HelpPostsPro.size();i++) {
            Map<String,String> provinceMap = new HashMap<String,String>();
            province = HelpPostsPro.get(i).getProvince();
            QueryWrapper wrapper1 = new QueryWrapper<>();
            wrapper1.like("province",province);
            int proNum = HelpPostService.list(wrapper1).size();
            provinceMap.put("province",province);
            provinceMap.put("num", String.valueOf(proNum));
            provinceData.add(provinceMap);
        }

        return provinceData;
    }

    @ApiOperation(value = "获取帮助信息市信息")
    @GetMapping("/cityData/{province}")
    public List<Map<String, String>> getCityData(@PathVariable("province") String province){
        QueryWrapper wrapper = new QueryWrapper<>();// 构建一个查询的wrapper
        wrapper.select("distinct city").like("province",province);
        List<HelpPost>  HelpPostsPro = HelpPostService.list(wrapper);
        List<Map<String, String>> provinceData = new ArrayList<Map<String,String>>();
        String city = null ;
        for(int i = 0 ; i < HelpPostsPro.size();i++) {
            Map<String,String> provinceMap = new HashMap<String,String>();
            city = HelpPostsPro.get(i).getCity();
            QueryWrapper wrapper1 = new QueryWrapper<>();
            wrapper1.like("city",city);
            int proNum = HelpPostService.list(wrapper1).size();
            provinceMap.put("province",province);
            provinceMap.put("city",city);
            provinceMap.put("num", String.valueOf(proNum));
            provinceData.add(provinceMap);
        }

        return provinceData;
    }

    @ApiOperation(value = "根据传入id获取帮助信息")
    @GetMapping("/postById")
    public HelpPost getHelpPostById(@RequestParam("id") Integer id){
        /**
        QueryWrapper<HelpPost> wrapper = new QueryWrapper<>();// 构建一个查询的wrapper
        wrapper.eq("id",id);
        HelpPost helpPost = HelpPostService.getOne(wrapper);
         **/
        HelpPost helpPost = HelpPostService.getById(id);
        //查询一次访问量+1
        int viewBef = helpPost.getViews();
        viewBef++;
        helpPost.setViews(viewBef);
        HelpPostService.updateById(helpPost);
        helpPost = HelpPostService.getById(id);
        return helpPost;
    }

    @ApiOperation(value = "添助帮助信息")
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

    @ApiOperation(value = "核实帮助信息")
    @PutMapping("/check")
    public RespBean checkHelpPost(@RequestParam("id") String id){
        HelpPost helpPost= HelpPostService.getById(id);
        helpPost.setChecked(true);
        if (HelpPostService.updateById(helpPost)){
            return RespBean.success("核实成功!");
        }
        return RespBean.error("核实失败！");
    }

    @ApiOperation(value = "取消核实帮助信息")
    @PutMapping("/unCheck")
    public RespBean unCheckHelpPost(@RequestParam("id") String id){
        HelpPost helpPost= HelpPostService.getById(id);
        helpPost.setChecked(false);
        if (HelpPostService.updateById(helpPost)){
            return RespBean.success("取消核实成功!");
        }
        return RespBean.error("取消核实失败！");
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
