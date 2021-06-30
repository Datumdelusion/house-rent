package com.datum.houserent.model.vo.core;

import com.datum.houserent.model.entity.House;

/**
 * @author datum
 * @date 2021/6/30 21:50
 */
public class HousePageQueryParam extends ConditionPageQueryParam<House> {

    @QueryField("location_three")
    private Integer locationId;

    @QueryField(value = "money_month",queryType = QueryField.QueryType.LE)
    private Double upperPrice;

}
