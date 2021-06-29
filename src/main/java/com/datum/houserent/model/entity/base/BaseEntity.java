package com.datum.houserent.model.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author violetfreesia
 * @date 2021-01-23
 */
@Data
@EqualsAndHashCode
public abstract class BaseEntity {

    @JsonIgnore
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JsonIgnore
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @JsonIgnore
    @ApiModelProperty(value = "是否已经删除")
    @TableLogic
    @TableField("deleted")
    private Integer deleted;

    /**
     * 获取实体主键
     *
     * @return 实体主键
     */
    public abstract Serializable getId();
}
