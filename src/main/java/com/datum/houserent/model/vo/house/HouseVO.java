package com.datum.houserent.model.vo.house;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.datum.houserent.model.core.OutputConverter;
import com.datum.houserent.model.entity.House;
import com.datum.houserent.model.entity.enums.HouseStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author datum
 * @date 2021/6/30 20:42
 */
@Data
@EqualsAndHashCode()
@Accessors(chain = true)
@ApiModel(value = "House对象", description = "")
public class HouseVO implements OutputConverter<HouseVO, House> {

    @ApiModelProperty("房源id")
    private Integer id;

    @ApiModelProperty(value = "房屋名称")
    private String name;

    @ApiModelProperty(value = "主图")
    private String head;

    @ApiModelProperty(value = "小区名称")
    private String neighbourhood;

    @ApiModelProperty(value = "面积")
    private Double area;

    @ApiModelProperty(value = "（枚举）户型：三室一厅")
    private String style;

    @ApiModelProperty(value = "经度")
    private Double longitude;

    @ApiModelProperty(value = "纬度")
    private Double latitude;

    @ApiModelProperty(value = "月付价格")
    @TableField("money_month")
    private Double moneyMonth;

    @ApiModelProperty(value = "热度浏览量")
    private Integer clout;

    @ApiModelProperty(value = "（bool）是否已出租")
    private HouseStatus status;
}
