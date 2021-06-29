package com.datum.houserent.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value = "Location对象", description = "")
public class Location extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    @ApiModelProperty(value = "拼音首字母")
    private String pinyin;

    @ApiModelProperty(value = "上级区域id")
    private Integer pid;

    @ApiModelProperty(value = "区域顺序")
    private String genealogy;

    @ApiModelProperty(value = "区域等级")
    private Integer level;

    @ApiModelProperty(value = "纬度")
    private Double latitude;

    @ApiModelProperty(value = "经度")
    private Double longitude;


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", pid=" + pid +
                ", genealogy='" + genealogy + '\'' +
                ", level=" + level +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                "}\n";
    }
}
