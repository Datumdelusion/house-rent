package com.datum.houserent.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 对象工具类
 *
 * @author violetfreesia
 * @since 2021-01-23
 */
@Slf4j
public class BeanUtil {
    /**
     * 获取某个类的所有字段 包括继承的父类的字段
     *
     * @param clazz 类
     * @return 返回字段对象列表
     */
    public static List<Field> getFields(Class<?> clazz) {
        Optional.ofNullable(clazz)
                .orElseThrow(() -> new NullPointerException("无法获取空对象的字段"));
        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        Class<?> clazzSuperclass = clazz.getSuperclass();
        // 遍历获取父类的属性
        while (clazzSuperclass != null) {
            fields.addAll(new ArrayList<>(Arrays.asList(clazzSuperclass.getDeclaredFields())));
            clazzSuperclass = clazzSuperclass.getSuperclass();
        }
        return fields;
    }

    /**
     * 获取某个类的所有字段
     *
     * @param clazz        类
     * @param ignoreSupper 是否忽略父类字段 默认为false
     * @return 返回字段对象列表
     */
    public static List<Field> getFields(Class<?> clazz, boolean ignoreSupper) {
        Optional.ofNullable(clazz)
                .orElseThrow(() -> new NullPointerException("无法获取空对象的字段"));
        return ignoreSupper ? new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())) : getFields(clazz);
    }

    /**
     * 获取两个字段集合在目标字段集合的交集
     *
     * @param sFields 原字段集
     * @param tFields 目标字段集合
     * @return 字段交集
     */
    public static List<Field> getIntersection(List<Field> sFields, List<Field> tFields) {
        Optional.ofNullable(sFields)
                .orElseThrow(() -> new NullPointerException("sFields 字段列表为空"));
        Optional.ofNullable(tFields)
                .orElseThrow(() -> new NullPointerException("tFields 字段列表为空"));
        return tFields.parallelStream()
                .filter(tField -> sFields
                        .parallelStream()
                        .anyMatch(sField -> sField.getType().equals(tField.getType())
                                && sField.getName().equals(tField.getName())))
                .collect(Collectors.toList());
    }

    /**
     * 拷贝一个对象的值到另一对象,采用的是浅拷贝,相同属性之间赋值
     * 原对象与目标对象可以是不同类型
     *
     * @param ignoreSupper 是否忽略父类字段 默认为false
     * @param sourceObj    原对象
     * @param targetObj    目标对象
     * @param ignoreNull   是否忽略空值
     */
    public static void convert(Object sourceObj, Object targetObj,
                               boolean ignoreSupper, boolean ignoreNull) {
        Optional.ofNullable(sourceObj)
                .orElseThrow(() -> new NullPointerException("原对象不能为空"));
        Optional.ofNullable(targetObj)
                .orElseThrow(() -> new NullPointerException("目标对象不能为空"));
        List<Field> sFields = getFields(sourceObj.getClass(), ignoreSupper);
        List<Field> tFields = getFields(targetObj.getClass(), ignoreSupper);
        // 获取字段交集
        List<Field> intersection = getIntersection(sFields, tFields);
        // 赋值转换
        intersection.parallelStream().map(field -> {
            ArrayList<Field> fields = new ArrayList<>();
            fields.add(field);
            sFields.parallelStream()
                    .filter(sField -> sField.getName().equals(field.getName()))
                    .findFirst()
                    .ifPresent(fields::add);
            return fields;
        }).forEach(fields -> {
            Field tField = fields.get(0);
            tField.setAccessible(true);
            Field sField = fields.get(1);
            sField.setAccessible(true);
            try {
                if (ignoreNull && sField.get(sourceObj) == null) {
                    return;
                }
                tField.set(targetObj, sField.get(sourceObj));
            } catch (IllegalAccessException e) {
                log.warn("已跳过字段: {} 原因: 无法访问 调整日志等级到DEBUG查看详情", tField);
                log.debug(Arrays.toString(e.getStackTrace()));
            }
        });
    }

    /**
     * 拷贝一个对象的值到另一对象,采用的是浅拷贝,相同属性之间赋值
     * 原对象与目标对象可以是不同类型
     *
     * @param ignoreSupper 是否忽略父类字段 默认为false
     * @param sourceObj    原对象
     * @param targetObj    目标对象
     */
    public static void convert(Object sourceObj, Object targetObj, boolean ignoreSupper) {
        convert(sourceObj, targetObj, ignoreSupper, false);
    }

    /**
     * 拷贝一个对象的值到另一对象,采用的是浅拷贝,相同属性之间赋值
     * 原对象与目标对象可以是不同类型
     *
     * @param sourceObj 原对象
     * @param targetObj 目标对象
     */
    public static void convert(Object sourceObj, Object targetObj) {
        convert(sourceObj, targetObj, false);
    }

    /**
     * 类型转换函数，将一个对象转换为另一个类的对象
     * 注意这里转换的时候采用的是浅拷贝
     *
     * @param sourceObj    原对象
     * @param tClass       目标类
     * @param ignoreSupper 是否忽略父类字段 默认为false
     * @param <T>          目标对象类型
     * @return 返回目标对象
     */
    public static <T> T convert(Object sourceObj, Class<T> tClass, boolean ignoreSupper) {
        Optional.ofNullable(tClass)
                .orElseThrow(() -> new NullPointerException("目标类不能为空"));
        T target;
        try {
            target = tClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("目标类没有无参构造函数");
        }
        convert(sourceObj, target, ignoreSupper);
        return target;
    }


    /**
     * 类型转换函数，将一个对象转换为另一个类的对象
     * 注意这里转换的时候采用的是浅拷贝
     *
     * @param sourceObj 原对象
     * @param tClass    目标类
     * @param <T>       目标对象类型
     * @return 返回目标对象
     */
    public static <T> T convert(Object sourceObj, Class<T> tClass) {
        return convert(sourceObj, tClass, false);
    }

    /**
     * 遍历转换集合对象里的每个对象
     *
     * @param sourceObjList 原类型对象集合
     * @param tClass        目标类型
     * @param <T>           目标类泛型
     * @param ignoreSupper  是否需要忽略父类字段
     * @return 返回目标类型对象集合
     */
    public static <T> List<T> convert(List<?> sourceObjList, Class<T> tClass, boolean ignoreSupper) {
        Optional.ofNullable(sourceObjList)
                .orElseThrow(() -> new NullPointerException("原对象集合不能为空"));
        Optional.ofNullable(tClass)
                .orElseThrow(() -> new NullPointerException("目标类不能为空"));
        if (sourceObjList.isEmpty()) {
            return Collections.emptyList();
        }
        Constructor<T> tConstructor;
        try {
            tConstructor = tClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("目标类没有可用构造函数");
        }
        return sourceObjList.parallelStream().map(sourceObj -> {
            T t;
            try {
                t = tConstructor.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("目标类没有无参构造函数");
            }
            convert(sourceObj, t, ignoreSupper);
            return t;
        }).collect(Collectors.toList());
    }

    /**
     * 遍历转换集合对象里的每个对象
     *
     * @param sourceObjList 原类型对象集合
     * @param tClass        目标类型
     * @param <T>           目标类泛型
     * @return 返回目标类型对象集合
     */
    public static <T> List<T> convert(List<?> sourceObjList, Class<T> tClass) {
        return convert(sourceObjList, tClass, false);
    }


}
