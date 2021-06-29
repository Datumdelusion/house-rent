package com.datum.houserent.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.datum.houserent.model.core.OutputConverter;
import com.datum.houserent.model.entity.Location;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author datum
 * @date 2021/6/29 21:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "地理信息", description = "")
public class LocationVO implements OutputConverter<LocationVO, Location> {

    @ApiModelProperty("地理信息id")
    private Integer id;

    @ApiModelProperty("地理名称")
    private String name;

    @JsonProperty("letter")
    @ApiModelProperty(value = "拼音")
    private String pinyin;

    @ApiModelProperty(value = "上级区域id")
    private Integer pid;

    @ApiModelProperty(value = "区域等级")
    private Integer level;
}
