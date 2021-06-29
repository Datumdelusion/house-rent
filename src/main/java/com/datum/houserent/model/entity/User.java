package com.datum.houserent.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.datum.houserent.model.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author datum
 * @since 2021-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String password;

    @ApiModelProperty(value = "（枚举）用户类型：0.管理员 1.租房者 2.出租者 3.租房和出租者")
    @TableField("user_type")
    private Integer userType;

    @ApiModelProperty(value = "头像")
    private String head;

    @ApiModelProperty(value = "微信openid")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(value = "电话")
    private String phone;


}
