package com.datum.houserent.model.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author datum
 * @date 2021/7/2 22:24
 */
public enum ContractStatus {

    /**
     * 成功
     */
    success(0),

    /**
     * 失败
     */
    failed(1),

    /**
     * 未完成
     */
    uncompleted(2);

    @EnumValue
    private final Integer id;

    ContractStatus(Integer id) {
        this.id = id;
    }

}
