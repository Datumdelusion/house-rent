package com.datum.houserent.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回数据包装类
 *
 * @author violetfreesia
 * @date 2021-05-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePackage {

    /**
     * 系统发生错误时的错误信息
     */
    private ErrorMessage errorMessage;

    /**
     * 请求成功时的数据
     */
    private Object data;


    public static ResponsePackage success(Object data) {
        return new ResponsePackage(null, data);
    }

    public static ResponsePackage fail(ErrorMessage errorMessage) {
        return new ResponsePackage(errorMessage, new Object());
    }
}
