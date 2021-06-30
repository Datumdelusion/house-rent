package com.datum.houserent.model.vo.core;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author violetfreesia
 * @date 2021-01-25
 */
public interface WrapperBuilder<T> {

    Logger log = LoggerFactory.getLogger("AbstractParams");

    /**
     * 根据该接口实现类所有带@QueryField注解的字段自动在传入的wrapper对象中添加条件查询
     *
     * @param wrapper   wrapper对象
     * @param fields    传入被@QueryField注解的字段集合
     * @param <Wrapper> wrapper对象的类型
     * @return 返回构建完成的wrapper对象
     */
    default <Wrapper extends AbstractWrapper<T, String, Wrapper>>
    Wrapper wrapperBuilder(Wrapper wrapper, List<Field> fields) {
        // 如果传的是null就去加载子类带@QueryField注解的字段
        if (fields == null) {
            Class<?> clazz = this.getClass();
            // 过滤掉没有@QueryField注解或OnlySetField为true的字段
            fields = Arrays.stream(clazz.getDeclaredFields()).parallel()
                    .filter(field -> field.isAnnotationPresent(QueryField.class)
                            && !field.getAnnotation(QueryField.class).OnlySetField())
                    .sorted(Comparator.comparingInt(field -> field.getAnnotation(QueryField.class).position()))
                    .collect(Collectors.toList());
        } else {
            fields = fields.parallelStream()
                    .sorted(Comparator.comparingInt(field -> field.getAnnotation(QueryField.class).position()))
                    .collect(Collectors.toList());
        }
        fields.forEach(field -> {
            try {
                field.setAccessible(true);
                Object fieldValue = field.get(this);
                if (fieldValue == null) {
                    return;
                }
                QueryField queryFieldRemark = field.getAnnotation(QueryField.class);
                // 获取字段对于的数据库表字段
                String tableFieldName = queryFieldRemark.value();
                if (queryFieldRemark.spliceWithOr()) {
                    wrapper.or();
                }
                // 根据查询类型调用对应的构造方法
                switch (queryFieldRemark.queryType()) {
                    case IS_NULL:
                        wrapper.isNull(tableFieldName);
                        break;
                    case IS_NOT_NULL:
                        wrapper.isNotNull(tableFieldName);
                        break;
                    case IN:
                        wrapper.in(fieldValue instanceof Collection<?>, tableFieldName, fieldValue);
                        break;
                    case NOT_IN:
                        wrapper.notIn(fieldValue instanceof Collection<?>, tableFieldName, fieldValue);
                        break;
                    case IN_SQL:
                        if (fieldValue instanceof String) {
                            wrapper.inSql(tableFieldName, (String) fieldValue);
                        }
                        break;
                    case NOT_IN_SQL:
                        if (fieldValue instanceof String) {
                            wrapper.notInSql(tableFieldName, (String) fieldValue);
                        }
                        break;
                    default:
                        try {
                            wrapper.getClass()
                                    .getMethod(queryFieldRemark.queryType().methodName(), Object.class, Object.class)
                                    .invoke(wrapper, tableFieldName, fieldValue);
                        } catch (Exception e) {
                            log.error("方法{}调用失败", queryFieldRemark.queryType().methodName());
                            throw new RuntimeException("方法调用异常");
                        }
                }
            } catch (IllegalAccessException e) {
                log.warn("获取字段:{}的值失败,已跳过", field.getName());
            }
        });
        return wrapper;
    }

    /**
     * 根据该接口实现类所有带@QueryField注解的字段自动构建条件查询
     *
     * @return 返回条件查询器 QueryWrapper<T>
     */
    default QueryWrapper<T> queryWrapper() {
        return wrapperBuilder(new QueryWrapper<>(), null);
    }

    /**
     * 根据该接口实现类所有带@QueryField注解的字段自动构建条件更新
     *
     * @return 返回条件更新器 QueryWrapper<T>
     */
    default UpdateWrapper<T> updateWrapper() {
        Class<?> clazz = this.getClass();
        // 获取所有带@QueryField注解的字段
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .parallel()
                .filter(field -> field.isAnnotationPresent(QueryField.class))
                .collect(Collectors.toList());
        // 过滤掉带onlySetField属性的字段
        List<Field> fieldsWithoutSetField = fields.parallelStream()
                .filter(field -> !field.getAnnotation(QueryField.class).OnlySetField())
                .collect(Collectors.toList());
        // 构建条件更新对象
        UpdateWrapper<T> updateWrapper = wrapperBuilder(new UpdateWrapper<>(), fieldsWithoutSetField);
        // 将所有带isSetField和OnlySetField属性的字段添加到条件更新器中
        fields.parallelStream()
                .filter(field -> field.getAnnotation(QueryField.class).OnlySetField()
                        || field.getAnnotation(QueryField.class).isSetField())
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        Object fieldValue = field.get(this);
                        QueryField queryFieldRemark = field.getAnnotation(QueryField.class);
                        // 获取字段对于的数据库表字段
                        String tableFieldName = queryFieldRemark.value();
                        updateWrapper.set(fieldValue != null, tableFieldName, fieldValue);
                    } catch (IllegalAccessException e) {
                        log.warn("获取字段: " + field.getName() + " 的值失败,已跳过");
                    }
                });
        return updateWrapper;
    }
}
