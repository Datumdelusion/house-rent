package com.datum.houserent.exception;

/**
 * Service exception
 *
 * @author violetfreesia
 * @date 2021-05-10
 */
public class ServiceException extends HouseRentRequestException {
    /**
     * @param errorCode     错误码
     * @param devMessage    错误调试信息
     * @param userPromptMsg 用户提示信息
     */
    public ServiceException(String devMessage, String userPromptMsg) {
        super(devMessage, userPromptMsg);
    }

    /**
     * @param errorCode     错误码
     * @param devMessage    错误调试信息
     * @param userPromptMsg 用户提示信息
     * @param cause         导致该异常的原因
     */
    public ServiceException(String devMessage, String userPromptMsg, Throwable cause) {
        super(devMessage, userPromptMsg, cause);
    }

}
