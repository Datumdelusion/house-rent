package com.datum.houserent.exception;


/**
 * Occur when domain is not found.
 *
 * @author violetfreesia
 * @date 2021-05-10
 */
public class NotFoundException extends HouseRentRequestException {
    public NotFoundException(String devMessage) {
        super(devMessage);
    }

    /**
     * @param devMessage    错误调试信息
     * @param userPromptMsg 用户提示信息
     */
    public NotFoundException(String devMessage, String userPromptMsg) {
        super(devMessage, userPromptMsg);
    }

    /**
     * @param errorCode     错误码
     * @param devMessage    错误调试信息
     * @param userPromptMsg 用户提示信息
     * @param cause         导致该异常的原因
     */
    public NotFoundException(String devMessage, String userPromptMsg, Throwable cause) {
        super(devMessage, userPromptMsg, cause);
    }

}
