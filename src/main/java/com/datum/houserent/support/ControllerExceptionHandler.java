package com.datum.houserent.support;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import com.datum.houserent.exception.HouseRentRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author datum
 * @date 2021/6/26 20:00
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponsePackage handleGlobalException(Exception e) {
        e.printStackTrace();
        return ResponsePackage.fail(new ErrorMessage(e.getMessage(), "服务器错误，等等再看看吧"));
    }

    @ExceptionHandler(HouseRentRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponsePackage houseRentExceptionHandler(HouseRentRequestException e) {
        return ResponsePackage.fail(new ErrorMessage(e.getDevMessage(), e.getUserPromptMsg()));
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponsePackage notLoginExceptionHandler(NotLoginException e) {
        return ResponsePackage.fail(new ErrorMessage(e.getMessage(), "请登录后再操作"));
    }

    @ExceptionHandler(NotRoleException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponsePackage notRoleExceptionHandler(NotRoleException e) {
        return ResponsePackage.fail(new ErrorMessage(e.getMessage(), "暂无操作权限"));
    }

}
