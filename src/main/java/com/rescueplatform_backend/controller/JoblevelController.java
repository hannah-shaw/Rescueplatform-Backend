package com.rescueplatform_backend.controller;


import com.rescueplatform_backend.entity.Joblevel;
import com.rescueplatform_backend.entity.RespBean;
import com.rescueplatform_backend.service.JoblevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hannah
 * @since 2022-02-03
 */
@Api(value = "职称管理",tags = "职称管理")
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    JoblevelService joblevelService;

    @ApiOperation("职称列表")
    @GetMapping("/list")
    public List<Joblevel> getAllJoblevel(){
        List<Joblevel> list = joblevelService.list();
        return list;
    }

    @ApiOperation("添加职称")
    @PostMapping("/add")
    public RespBean addJoblevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation("修改职称")
    @PutMapping("/update")
    public RespBean updateJoblevel(@RequestBody Joblevel joblevel){
        if (joblevelService.updateById(joblevel)){

            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation("删除职称")
    @DeleteMapping("/delete")
    public RespBean deleteJoblevel(@RequestParam("id")Long id){

        joblevelService.removeById(id);
        return RespBean.success("删除成功！");
    }

    @ApiOperation("批量删除职称")
    @DeleteMapping("/delete/ids")
    public RespBean deleteByIds(@RequestParam("ids") List<Long> ids){
        joblevelService.removeByIds(ids);

        return RespBean.success("删除成功");
    }
}
