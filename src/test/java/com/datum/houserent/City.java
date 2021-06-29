package com.datum.houserent;

/**
 * @author datum
 * @date 2021/6/28 21:10
 */
public class City extends Province {
    private Integer city;

    public City(Integer province, Integer city, String name) {
        super(province, name);
        this.city = city;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "province:" + super.getProvince() +
                " city:" + city +
                " name" + super.getName();
    }
}
