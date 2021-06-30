package com.datum.houserent.model.vo.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记该字段需要被作为条件查询的条件
 *
 * @author violetfreesia
 * @date 2021-01-24
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryField {
    /**
     * 设置改字段对应的表字段名
     */
    String value();

    /**
     * 字段已何种查询方式加入到条件构造器中, 默认采用等于的方式
     */
    QueryType queryType() default QueryType.EQ;

    /**
     * 是否将改字段作为排序字段,仅仅用于QueryWrapper
     */
    boolean orderBy() default false;

    /**
     * 是否升序排序, 该属性为false时,orderBy无需设置,默认为true
     */
    boolean isisAsc() default true;

    /**
     * 将改字段以或的方式加入到条件中, 也就是调用条件构造器的or()函数拼接, 默认调用and()
     */
    boolean spliceWithOr() default false;

    /**
     * 是否作为构造UpdateWrapper的set字段
     */
    boolean isSetField() default false;

    /**
     * 是否仅仅作为构造UpdateWrapper的set字段,该属性为true时,isSetField无需设置,默认为true
     */
    boolean OnlySetField() default false;

    /**
     * 仅在value 为BETWEEN是有效
     * 用于标记between And 所需要的两个参数 值一样即为同组 如果同组不是两个值就会报错
     */
    int betweenId() default -1;

    /**
     * 字段位置,用于构造时的字段排序
     */
    int position() default 1;

    /**
     * 查询类型枚举
     */
    enum QueryType {
        // == equal
        EQ("eq"),
        // != not equal
        NE("ne"),
        // >
        GT("gt"),
        // >=
        GE("ge"),
        // <
        LT("lt"),
        // <=
        LE("le"),
        // like %值%
        LIKE("like"),
        // not like
        NOT_LIKE("notLike"),
        // like left %值
        LIKE_LEFT("likeLeft"),
        // like right 值%
        LIKE_RIGHT("likeRight"),
        // is null
        IS_NULL("isNull"),
        // is not null
        IS_NOT_NULL("isNotNull"),
        // in
        IN("in"),
        // not in
        NOT_IN("notIn"),
        // in sql
        IN_SQL("inSql"),
        // not in sql
        NOT_IN_SQL("notInSql");

        /**
         * 记录枚举对象对应的方法名
         */
        private final String methodName;

        QueryType(String methodName) {
            this.methodName = methodName;
        }

        public String methodName() {
            return this.methodName;
        }
    }
}
