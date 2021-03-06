package com.datum.houserent.model.vo.queryCondition;

import com.datum.houserent.model.entity.House;
import com.datum.houserent.model.vo.core.ConditionPageQueryParam;
import com.datum.houserent.model.vo.core.QueryField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author datum
 * @date 2021/6/30 21:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HousePageQueryParam extends ConditionPageQueryParam<House> {

    @ApiModelProperty("地理位置区类型名称")
    private String locationName;

    @ApiModelProperty("地理位置id（区类型）")
    @QueryField("location_three")
    private Integer locationThree;

    @ApiModelProperty("地理位置id（市类型）")
    @QueryField("location_two")
    private Integer locationTwo;

    @ApiModelProperty("地理位置id（省类型）")
    @QueryField("location_one")
    private Integer locationOne;

    @ApiModelProperty("最大价格")
    @QueryField(value = "money_month", queryType = QueryField.QueryType.LE)
    private Double upperPrice;

    @ApiModelProperty("最低价格")
    @QueryField(value = "money_month", queryType = QueryField.QueryType.GE)
    private Double lowerPrice;

    @ApiModelProperty("搜索房名")
    @QueryField(value = "name",queryType = QueryField.QueryType.LIKE)
    private String name;
}
