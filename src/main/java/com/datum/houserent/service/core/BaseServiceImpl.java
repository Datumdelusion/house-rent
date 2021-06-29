package com.datum.houserent.service.core;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.datum.houserent.exception.NotFoundException;
import com.datum.houserent.model.entity.base.BaseEntity;
import com.datum.houserent.utils.BeanUtil;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

/**
 * 顶级Service实现类
 *
 * @author violetfreesia
 * @date 2021-03-11
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {

    /**
     * 根据id检查是否存在
     *
     * @param id id
     * @return 存在与否
     */
    @Override
    public boolean existById(Serializable id) {
        Class<T> entityClass = getEntityClass();
        List<Field> fields = BeanUtil.getFields(entityClass);
        // 获取主键字段
        Optional<Field> keyField = fields.parallelStream()
                .filter(field -> field.isAnnotationPresent(TableId.class))
                .findFirst();
        if (keyField.isPresent()) {
            String columnName = keyField.get().getAnnotationsByType(TableId.class)[0].value();
            if ("".equals(columnName)) {
                throw new NotFoundException("未获取到实体类的主键字段名");
            }
            T one = getOne(new QueryWrapper<T>().select(columnName).eq(columnName, id));
            return one != null;
        } else {
            throw new NotFoundException("未找到被@TableId注解的字段");
        }
    }

    /**
     * 根据个字段值查询记录是否存在
     *
     * @param column 字段名
     * @param value  字段值
     * @return 存在与否
     */
    @Override
    public boolean existByColumn(String column, Object value) {
        int count = count(new QueryWrapper<T>().eq(column, value));
        return count > 0;
    }

    /**
     * 根据主键获取记录
     *
     * @param id 主键
     * @return 返回查询结果
     */
    @Override
    public Optional<T> getNullableById(Serializable id) {
        return Optional.ofNullable(getById(id));
    }
}
