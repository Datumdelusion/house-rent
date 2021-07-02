package com.datum.houserent.model.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author datum
 * @date 2021/7/1 21:53
 */
public enum HouseStatus {

    /**
     * 上架状态
     */
    @ApiModelProperty("未出租")
    OnShelf(0),

    /**
     * 下架（已出租）状态
     */
    @ApiModelProperty("已出租")
    OffShelf(1);

    @EnumValue
    private final Integer id;

    HouseStatus(Integer id) {
        this.id = id;
    }
}
