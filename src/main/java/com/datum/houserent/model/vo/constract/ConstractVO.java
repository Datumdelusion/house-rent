package com.datum.houserent.model.vo.constract;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.datum.houserent.model.core.OutputConverter;
import com.datum.houserent.model.entity.Contract;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author datum
 * @date 2021/7/2 22:18
 */
@Data
public class ConstractVO implements OutputConverter<ConstractVO, Contract> {

    @ApiModelProperty("合约id")
    private Integer id;

    @ApiModelProperty("房源id")
    private Integer houseId;

    @ApiModelProperty("房源名称")
    private String houseName;

    @ApiModelProperty(value = "租房者")
    private Integer userId;

    @ApiModelProperty(value = "出租者")
    private Integer lessor;

    @ApiModelProperty(value = "租赁期限")
    private LocalDate time;

    @ApiModelProperty(value = "价钱")
    private Double rentMoney;

    @ApiModelProperty(value = "（bool）出租人确认")
    private Boolean lessorSign;

    @ApiModelProperty(value = "（bool）租房人确认")
    private Boolean userSign;

    @ApiModelProperty(value = "（bool）合约是否成功")
    private Boolean success;

}
