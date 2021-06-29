package com.datum.houserent;

import java.util.List;

/**
 * @author datum
 * @date 2021/6/28 20:43
 */
public class Province {
    private Integer province;

    private String name;

    public Province() {
    }

    public Province(Integer province,String name) {
        this.province = province;
        this.name = name;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "province:" + province + " name:" + name;
    }
}
