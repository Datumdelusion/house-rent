package com.datum.houserent.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author violetfreesia
 * @date 2021-05-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    /**
     * 调试信息
     */
    private String devMessage;

    /**
     * 用户提示信息
     */
    private String userPromptMsg;
}
