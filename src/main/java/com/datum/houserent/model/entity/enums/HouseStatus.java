package com.datum.houserent.model.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author datum
 * @date 2021/7/1 21:53
 */
public enum HouseStatus {

    /**
     * 上架状态
     */
    OnShelf(0),

    /**
     * 下架状态
     */
    OffShelf(1);

    @EnumValue
    private final Integer id;

    HouseStatus(Integer id) {
        this.id = id;
    }
}
