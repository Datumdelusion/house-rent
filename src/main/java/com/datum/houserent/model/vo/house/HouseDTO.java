package com.datum.houserent.model.vo.house;

import com.baomidou.mybatisplus.annotation.TableField;
import com.datum.houserent.model.core.InputConverter;
import com.datum.houserent.model.entity.House;
import com.datum.houserent.model.entity.enums.OrientationType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author datum
 * @date 2021/7/1 20:38
 */

@Data
public class HouseDTO implements InputConverter<House> {
    @NotBlank
    @ApiModelProperty(value = "房屋名称")
    private String name;

    @NotBlank
    @ApiModelProperty(value = "主图")
    private String head;

    @NotNull
    @ApiModelProperty(value = "（json）房屋图片")
    private String[] pics;

    @NotNull
    @ApiModelProperty(value = "一级区域")
    @TableField("location_one")
    private Integer locationOne;

    @NotNull
    @ApiModelProperty(value = "二级区域")
    @TableField("location_two")
    private Integer locationTwo;

    @NotNull
    @ApiModelProperty(value = "三级区域")
    @TableField("location_three")
    private String locationThreeName;

    @NotNull
    @ApiModelProperty(value = "小区名称")
    private String neighbourhood;

    @NotNull
    @ApiModelProperty(value = "具体位置")
    @TableField("detail_location")
    private String detailLocation;

    @ApiModelProperty(value = "（json）房屋配套设施")
    private String[] usp;

    @NotNull
    @ApiModelProperty(value = "面积")
    private Double area;

    @NotNull
    @ApiModelProperty(value = "（枚举）房屋朝向")
    private OrientationType orientation;

    @NotNull
    @ApiModelProperty(value = "（枚举）户型：三室一厅")
    private String style;

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

    @NotNull
    @ApiModelProperty(value = "月付价格")
    @TableField("money_month")
    private Double moneyMonth;
}
