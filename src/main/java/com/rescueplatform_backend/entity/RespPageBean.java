package com.rescueplatform_backend.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: yeb
 * @description: 分页公共返回对象
 * @author: Honors
 * @create: 2021-07-20 09:31
 */
@Api(value = "分页",tags = "分页")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {

    @ApiModelProperty(value = "总条数")
    private Long  total;

    @ApiModelProperty(value = "数据List")
    private List<?> date;

}