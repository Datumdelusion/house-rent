package com.datum.houserent;

/**
 * @author datum
 * @date 2021/6/28 21:10
 */
public class City extends Province {
    private String city;

    public City(String province, String city, String name) {
        super(province, name);
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "province:" + super.getProvince() +
                " city:" + city +
                " name" + super.getName();
    }
}
