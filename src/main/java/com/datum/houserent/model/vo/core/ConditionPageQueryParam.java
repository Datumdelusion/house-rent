package com.datum.houserent.model.vo.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 条件分页查询
 *
 * @author violetfreesia
 * @date 2021-01-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ConditionPageQueryParam<T> extends PageQueryParam<T> implements WrapperBuilder<T> {
    @Override
    public QueryWrapper<T> sortWrapper() {
        boolean isAsc = getIsAsc() != null && getIsAsc();
        QueryWrapper<T> queryWrapper = queryWrapper();
        if (!"".equals(getSortBy())) {
            queryWrapper.orderBy(true, isAsc, getSortBy());
        }
        return queryWrapper;
    }
}
