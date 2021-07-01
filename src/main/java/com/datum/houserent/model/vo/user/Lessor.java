package com.datum.houserent.model.vo.user;

import com.datum.houserent.model.core.OutputConverter;
import com.datum.houserent.model.entity.User;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author datum
 * @date 2021/7/1 22:19
 */
public class Lessor implements OutputConverter<Lessor, User> {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("电话")
    private String phone;

}
