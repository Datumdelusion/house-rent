package com.datum.houserent.model.vo.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.datum.houserent.model.core.InputConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author violetfreesia
 * @date 2021-03-10
 */
@Data
public class SortParam<T> implements InputConverter<T> {
    @ApiModelProperty("排序字段,默认以修改时间排序")
    private String sortBy;

    @ApiModelProperty("是否升序(默认为降序,仅在SortBy字段不为空时有效)")
    private Boolean isAsc;

    /**
     * 排序wrapper构造器
     *
     * @return 排序wrapper
     */
    public QueryWrapper<T> sortWrapper() {
        isAsc = isAsc != null && isAsc;
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (sortBy == null || "".equals(sortBy)) {
            sortBy = "update_time";
        }
        return queryWrapper.orderBy(true, isAsc, sortBy);
    }
}
