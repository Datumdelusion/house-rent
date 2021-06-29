package com.datum.houserent.exception;

/**
 * @author datum
 * @date 2021/6/26 20:04
 */
public class HouseRentRequestException extends RuntimeException {

    /**
     * 调试信息
     */
    private String devMessage;

    /**
     * 用户提示信息
     */
    private String userPromptMsg;

    public HouseRentRequestException(String devMessage) {
        super(devMessage);
        this.devMessage = devMessage;
    }

    public HouseRentRequestException(String devMessage, String userPromptMsg) {
        super(devMessage);
        this.devMessage = devMessage;
        this.userPromptMsg = userPromptMsg;
    }

    public HouseRentRequestException(String devMessage, String userPromptMsg, Throwable cause) {
        super(devMessage, cause);
        this.devMessage = devMessage;
        this.userPromptMsg = userPromptMsg;
    }

    public HouseRentRequestException(Throwable cause) {
        super(cause);
    }

    public String getDevMessage() {
        return devMessage;
    }

    public String getUserPromptMsg() {
        return userPromptMsg;
    }

}
