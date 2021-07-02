package com.datum.houserent.model.vo.core;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.datum.houserent.model.core.OutputConverter;
import com.datum.houserent.model.entity.House;
import com.datum.houserent.model.entity.Intention;
import com.datum.houserent.model.entity.User;
import com.datum.houserent.model.vo.house.HouseVO;
import com.datum.houserent.model.vo.user.UserVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author datum
 * @date 2021/7/2 20:14
 */
@Data
public class IntentionVO implements OutputConverter<IntentionVO, Intention> {
    @ApiModelProperty("意向id")
    private Integer id;
    @ApiModelProperty("用户")
    private UserVO user;
    @ApiModelProperty("房屋信息")
    private HouseVO house;
    @ApiModelProperty("出租者id")
    private Integer lessor;

}
