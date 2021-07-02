package com.datum.houserent.model.vo.house;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.datum.houserent.model.core.OutputConverter;
import com.datum.houserent.model.entity.Star;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author datum
 * @date 2021/7/2 18:17
 */
@Data
public class StarHouse implements OutputConverter<StarHouse, Star> {

    @ApiModelProperty("收藏id")
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    private Integer houseId;

    @ApiModelProperty("房屋信息")
    private HouseVO house;

}
