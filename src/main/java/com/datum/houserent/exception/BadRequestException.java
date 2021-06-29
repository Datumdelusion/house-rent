package com.datum.houserent.exception;


/**
 * Bad request exception.
 *
 * @author violetfreesia
 * @date 2021-05-10
 */
public class BadRequestException extends HouseRentRequestException {

    public BadRequestException(String devMessage) {
        super(devMessage);
    }

    /**
     * @param devMessage    错误调试信息
     * @param userPromptMsg 用户提示信息
     */
    public BadRequestException(String devMessage, String userPromptMsg) {
        super(devMessage, userPromptMsg);
    }

    /**
     * @param errorCode     错误码
     * @param devMessage    错误调试信息
     * @param userPromptMsg 用户提示信息
     * @param cause         导致该异常的原因
     */
    public BadRequestException(String devMessage, String userPromptMsg, Throwable cause) {
        super(devMessage, userPromptMsg, cause);
    }
}
