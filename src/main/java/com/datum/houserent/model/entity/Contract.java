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
@ApiModel(value = "Contract对象", description = "")
public class Contract extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("house_id")
    private Integer houseId;

    @ApiModelProperty(value = "租房者")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "出租者")
    private Integer lessor;

    @ApiModelProperty(value = "租赁期限（单位/月）")
    private Integer time;

    @ApiModelProperty(value = "价钱")
    @TableField("rent_money")
    private Double rentMoney;

    @ApiModelProperty(value = "（bool）出租人确认")
    @TableField("lessor_sign")
    private Integer lessorSign;

    @ApiModelProperty(value = "（bool）租房人确认")
    @TableField("user_sign")
    private Integer userSign;

    @ApiModelProperty(value = "（bool）合约是否成功")
    private Integer success;


}
