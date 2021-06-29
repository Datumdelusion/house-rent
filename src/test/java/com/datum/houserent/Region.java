package com.datum.houserent;

/**
 * @author datum
 * @date 2021/6/28 21:12
 */
public class Region extends City {
    private String region;

    public Region(String province, String city, String region, String name) {
        super(province, city, name);
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "province:" + super.getProvince() +
                " city:" + super.getCity() +
                " region:" + region +
                " name:" + getName();
    }
}
