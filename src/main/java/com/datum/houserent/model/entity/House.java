package com.datum.houserent.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.datum.houserent.model.entity.base.BaseEntity;
import java.time.LocalDate;
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
 * @author datum
 * @since 2021-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "House对象", description = "")
public class House extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "出租者")
    private Integer lessor;

    @ApiModelProperty(value = "房屋名称")
    private String name;

    @ApiModelProperty(value = "主图")
    private String head;

    @ApiModelProperty(value = "（json）房屋图片")
    private String pics;

    @ApiModelProperty(value = "一级区域")
    @TableField("location_one")
    private Integer locationOne;

    @ApiModelProperty(value = "二级区域")
    @TableField("location_two")
    private Integer locationTwo;

    @ApiModelProperty(value = "三级区域")
    @TableField("location_three")
    private Integer locationThree;

    @ApiModelProperty(value = "小区名称")
    private String neighbourhood;

    @ApiModelProperty(value = "具体位置")
    @TableField("detail_location")
    private String detailLocation;

    @ApiModelProperty(value = "（json）房屋配套设施")
    private String usp;

    @ApiModelProperty(value = "面积")
    private Double area;

    @ApiModelProperty(value = "（枚举）房屋朝向")
    private Integer orientation;

    @ApiModelProperty(value = "（枚举）户型：三室一厅")
    private Integer style;

    @ApiModelProperty(value = "楼层")
    private Integer storey;

    @ApiModelProperty(value = "(bool)是否电梯")
    private Boolean elevator;

    @ApiModelProperty(value = "建成年份")
    private Integer years;

    @ApiModelProperty(value = "绿化面积")
    @TableField("green_area")
    private Double greenArea;

    @ApiModelProperty(value = "房屋概述")
    private String brief;

    @ApiModelProperty(value = "最快入住时间")
    @TableField("check_in_time")
    private LocalDate checkInTime;

    @ApiModelProperty(value = "最低租赁时长（单位/月）")
    @TableField("lease_term")
    private Integer leaseTerm;

    @ApiModelProperty(value = "纬度")
    private Double latitude;

    @ApiModelProperty(value = "经度")
    private Double longitude;

    @ApiModelProperty(value = "月付价格")
    @TableField("money_month")
    private Double moneyMonth;

    @ApiModelProperty(value = "季付价格")
    @TableField("money_season")
    private Double moneySeason;

    @ApiModelProperty(value = "年付价格")
    @TableField("money_year")
    private Double moneyYear;

    @ApiModelProperty(value = "（bool）是否上架")
    private Integer status;

    @ApiModelProperty(value = "热度浏览量")
    private Integer clout;


}
