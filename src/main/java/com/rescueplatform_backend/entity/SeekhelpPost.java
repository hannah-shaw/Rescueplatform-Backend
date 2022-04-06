package com.rescueplatform_backend.entity;

import java.math.BigDecimal;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hannah
 * @since 2022-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_seekhelp_post")
@ApiModel(value="SeekhelpPost对象", description="")
public class SeekhelpPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "经度")
    @Excel(name = "经度",width = 30)
    private BigDecimal posx;

    @ApiModelProperty(value = "纬度")
    @Excel(name = "纬度",width = 30)
    private BigDecimal posy;

    @ApiModelProperty(value = "联系电话")
    @Excel(name = "联系电话")
    private String phone;

    @ApiModelProperty(value = "姓名")
    @Excel(name = "姓名")
    private String name;

    @ApiModelProperty(value = "情况描述")
    @Excel(name = "情况描述",width = 50)
    private String discription;

    @ApiModelProperty(value = "浏览量")
    @Excel(name = "浏览量")
    private Integer views;

    @ApiModelProperty(value = "是否安全 1：安全  ")
    @Excel(name = "是否安全")
    private Boolean safed;

    @ApiModelProperty(value = "是否核实 1：核实")
    @Excel(name = "是否核实")
    private Boolean checked;

    @ApiModelProperty(value = "受灾人数")
    private Integer people;

    @ApiModelProperty(value = "1：有小孩")
    @Excel(name = "人员中是否有小孩")
    private Boolean child;

    @ApiModelProperty(value = "1：有老人")
    @Excel(name = "人员中是否有老人")
    private Boolean old;

    @ApiModelProperty(value = "省")
    @Excel(name = "省")
    private String province;

    @ApiModelProperty(value = "市")
    @Excel(name = "市")
    private String city;

    @ApiModelProperty(value = "区")
    @Excel(name = "具体位置",width = 40)
    private String district;

    @ApiModelProperty(value = "发布时间")
    @Excel(name = "发布时间",width = 30,format = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate createtime;


}
