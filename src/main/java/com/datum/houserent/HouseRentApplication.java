package com.datum.houserent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author datum
 */
@SpringBootApplication
@MapperScan("com.datum.houserent.dao.mapper")
public class HouseRentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseRentApplication.class, args);
    }

}

