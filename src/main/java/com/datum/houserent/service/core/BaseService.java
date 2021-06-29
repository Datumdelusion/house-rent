package com.datum.houserent.service.core;

import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Optional;

/**
 * 顶级Service
 *
 * @author violetfreesia
 * @date 2021-03-11
 */
public interface BaseService<T> extends IService<T> {
    /**
     * 根据id检查是否存在
     *
     * @param id id
     * @return 存在与否
     */
    boolean existById(Serializable id);

    /**
     * 根据个字段值查询记录是否存在
     *
     * @param column 字段名
     * @param value  字段值
     * @return 存在与否
     */
    boolean existByColumn(String column, Object value);

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return 返回查询结果
     */
    Optional<T> getNullableById(Serializable id);
}
