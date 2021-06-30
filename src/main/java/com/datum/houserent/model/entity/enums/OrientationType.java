package com.datum.houserent.model.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author datum
 * @date 2021/6/30 15:34
 */
public enum OrientationType {
    /**
     * 朝东
     */
    EAST(1),

    /**
     * 朝西
     */
    WEST(2),

    /**
     * 朝南
     */
    SOUTH(3),

    /**
     * 北
     */
    NORTH(4);

    @EnumValue
    private final Integer id;

    OrientationType(Integer id) {
        this.id = id;
    }

   public static OrientationType getType(String target) {
        if ("朝东".equals(target)) {
            return OrientationType.EAST;
        }
        if ("朝西".equals(target)) {
            return OrientationType.WEST;
        }
        if ("朝南".equals(target)) {
            return OrientationType.SOUTH;
        }
        if ("朝北".equals(target)) {
            return OrientationType.NORTH;
        }
        return null;
    }

}
