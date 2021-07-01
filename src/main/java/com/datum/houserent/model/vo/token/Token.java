package com.datum.houserent.model.vo.token;

import lombok.Data;

/**
 * @author datum
 * @date 2021/7/1 20:25
 */
@Data
public class Token {
    private String saToken;

    public Token(String saToken) {
        this.saToken = saToken;
    }
}
