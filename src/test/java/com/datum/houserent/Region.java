package com.datum.houserent;

/**
 * @author datum
 * @date 2021/6/28 21:12
 */
public class Region extends City {
    private Integer region;

    public Region(Integer province, Integer city, Integer region, String name) {
        super(province, city, name);
        this.region = region;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
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
