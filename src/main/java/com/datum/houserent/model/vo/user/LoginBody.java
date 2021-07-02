package com.datum.houserent.model.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author datum
 * @date 2021/7/2 16:00
 */
@Data
public class LoginBody {

    @NotBlank
    @ApiModelProperty("用户名")
    private String name;

    @NotBlank
    @ApiModelProperty("密码")
    private String password;
}
