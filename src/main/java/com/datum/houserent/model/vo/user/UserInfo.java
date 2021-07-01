package com.datum.houserent.model.vo.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * @author datum
 * @date 2021/7/1 18:55
 */
public class UserInfo {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户头像")
    private String head;

}
