package com.datum.houserent.model.vo.house;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.datum.houserent.model.core.OutputConverter;
import com.datum.houserent.model.entity.House;
import com.datum.houserent.model.entity.enums.HouseStatus;
import com.datum.houserent.model.entity.enums.OrientationType;
import com.datum.houserent.model.vo.user.UserVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author datum
 * @date 2021/7/1 21:33
 */
@Data
public class HouseDetailVO implements OutputConverter<HouseDetailVO, House> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "出租者")
    private UserVO lessor;

    @ApiModelProperty(value = "房屋名称")
    private String name;

    @ApiModelProperty(value = "主图")
    private String head;

    @ApiModelProperty(value = "（json）房屋图片")
    private String[] pics;

    @ApiModelProperty(value = "一级区域")
    private String locationOneName;

    @ApiModelProperty(value = "二级区域")
    private String locationTwoName;

    @ApiModelProperty(value = "三级区域")
    private String locationThreeName;

    @ApiModelProperty(value = "小区名称")
    private String neighbourhood;

    @ApiModelProperty(value = "具体位置")
    private String detailLocation;

    @ApiModelProperty(value = "（json）房屋配套设施")
    private String[] usp;

    @ApiModelProperty(value = "面积")
    private Double area;

    @ApiModelProperty(value = "（枚举）房屋朝向")
    private OrientationType orientation;

    @ApiModelProperty(value = "（枚举）户型：三室一厅")
    private String style;

    @ApiModelProperty(value = "楼层")
    private Integer storey;

    @ApiModelProperty(value = "(bool)是否电梯")
    private Boolean elevator;

    @ApiModelProperty(value = "建成年份")
    private Integer years;

    @ApiModelProperty(value = "绿化面积")
    private Double greenArea;

    @ApiModelProperty(value = "房屋概述")
    private String brief;

    @ApiModelProperty(value = "最快入住时间")
    private LocalDate checkInTime;

    @ApiModelProperty(value = "最低租赁时长（单位/月）")
    private Integer leaseTerm;

    @ApiModelProperty(value = "纬度")
    private Double latitude;

    @ApiModelProperty(value = "经度")
    private Double longitude;

    @ApiModelProperty(value = "月付价格")
    private Double moneyMonth;

    @ApiModelProperty(value = "热度浏览量")
    private Integer clout;

    @ApiModelProperty(value = "（bool）是否已出租")
    private HouseStatus status;

    @ApiModelProperty("是否收藏")
    private Boolean star;
}
