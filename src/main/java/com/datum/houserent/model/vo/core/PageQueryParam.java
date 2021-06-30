package com.datum.houserent.model.vo.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author violetfreesia
 * @date 2021-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageQueryParam<T> extends SortParam<T> {

    @NotNull
    @ApiModelProperty(value = "当前分页", required = true)
    private Long current;

    @ApiModelProperty("分页尺寸,默认为10")
    private Long size;

    public Page<T> page() {
        Page<T> page = new Page<>(current, 10);
        return size == null ? page : page.setSize(size);
    }
}
