package com.datum.houserent;

import java.util.List;

/**
 * @author datum
 * @date 2021/6/28 20:43
 */
public class Province {
    private String province;

    private String name;

    public Province() {
    }

    public Province(String province,String name) {
        this.province = province;
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
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
