package com.datum.houserent.model.core;


import com.datum.houserent.utils.BeanUtil;

/**
 * @author violetfreesia
 * @date 2021-03-14
 */
public interface OutputConverter<Child extends OutputConverter<Child, T>, T> {

    /**
     * 将T对象转换为当前对象
     *
     * @param inObject T对象
     * @return 子类对象
     */
    @SuppressWarnings("unchecked")
    default Child convertIn(T inObject) {
        BeanUtil.convert(inObject, this);
        return (Child) this;
    }
}
