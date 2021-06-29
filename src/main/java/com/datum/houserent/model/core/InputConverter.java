package com.datum.houserent.model.core;

import com.datum.houserent.utils.BeanUtil;

import java.lang.reflect.ParameterizedType;

/**
 * @author violetfreesia
 * @date 2021-03-14
 */
public interface InputConverter<T> {

    /**
     * 将当前对象转为T的对象
     *
     * @return 返回T的对象
     */
    @SuppressWarnings("unchecked")
    default T convertOut() {
        // 获得子类的Class
        Class<?> clazz = this.getClass();
        // 获得实现类的父接口的泛型参数(就是这里这个T)
        // 注意: 我这里直接取的第一个值, 前提是子类实现该接口时必须将BaseIOMethod接口放在第一个
        ParameterizedType pt = (ParameterizedType) clazz.getGenericInterfaces()[0];
        // 获得子类第一个实际泛型参数的Class（T.class）
        Class<T> tClass = (Class<T>) pt.getActualTypeArguments()[0];
        return BeanUtil.convert(this, tClass);
    }

    /**
     * 使用当前类已有属性更新目标对象(会自动跳过值为null的字段)
     *
     * @param tObject 目标对象
     */
    default void convertOut(T tObject) {
        BeanUtil.convert(this, tObject, false, true);
    }

    /**
     * 将当前子类对象转为目标类对象
     *
     * @param tClass 目标类
     * @param <S>    目标类泛型
     * @return 返回目标类对象
     */
    default <S> S convertOut(Class<S> tClass) {
        return BeanUtil.convert(this, tClass);
    }
}
