package com.datum.houserent.model.vo.user;

import com.datum.houserent.model.core.OutputConverter;
import com.datum.houserent.model.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author datum
 * @date 2021/7/1 22:19
 */
@Data
@ApiModel("出租人信息")
public class UserVO implements OutputConverter<UserVO, User> {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("电话")
    private String phone;

}
