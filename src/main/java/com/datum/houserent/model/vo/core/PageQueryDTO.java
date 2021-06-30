package com.datum.houserent.model.vo.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.datum.houserent.utils.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * @author violetfreesia
 * @date 2021-01-24
 */
@Data
public class PageQueryDTO<T> {
    @ApiModelProperty("当前页数")
    private Long current;

    @ApiModelProperty("分页尺寸")
    private Long size;

    @ApiModelProperty("总记录数")
    private Long total;

    @ApiModelProperty("查询结果")
    private List<T> records;

    /**
     * 空分页函数
     *
     * @param current 当前页数
     * @param size    分页尺寸
     * @param <T>     分页数据类型
     * @return 空分页
     */
    public static <T> PageQueryDTO<T> emptyPage(Long current, Long size) {
        Assert.notNull(current, "当前分页不能为null");
        Assert.notNull(size, "分页尺寸不能为null");

        PageQueryDTO<T> queryDTO = new PageQueryDTO<>();
        queryDTO.setSize(size);
        queryDTO.setCurrent(current);
        queryDTO.setTotal(0L);
        queryDTO.setRecords(Collections.emptyList());
        return queryDTO;
    }

    /**
     * 将{@link Page<R>}对象转为{@link PageQueryDTO<T>}对象
     *
     * @param page   {@link Page<R>} 对象
     * @param tClass T的类
     * @return 返回PageQueryDTO<T>对象
     */
    public static <T, R> PageQueryDTO<T> convertIn(Page<R> page, Class<T> tClass) {
        PageQueryDTO<T> pageQueryDTO = new PageQueryDTO<>();
        pageQueryDTO.current = page.getCurrent();
        pageQueryDTO.size = page.getSize();
        pageQueryDTO.total = page.getTotal();
        pageQueryDTO.records = BeanUtil.convert(page.getRecords(), tClass);
        return pageQueryDTO;
    }

    /**
     * 将{@link Page<T>}对象转为{@link PageQueryDTO<T>}对象
     *
     * @param page {@link Page<T>} 对象
     * @return 返回PageQueryDTO<T>对象
     */
    public static <T> PageQueryDTO<T> convertIn(Page<T> page) {
        PageQueryDTO<T> pageQueryDTO = new PageQueryDTO<>();
        BeanUtil.convert(page, pageQueryDTO);
        pageQueryDTO.current = page.getCurrent();
        pageQueryDTO.total = page.getTotal();
        pageQueryDTO.size = page.getSize();
        return pageQueryDTO;
    }

}
