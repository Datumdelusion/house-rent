package com.datum.houserent.controller;


import com.datum.houserent.model.vo.HouseVO;
import com.datum.houserent.model.vo.core.HousePageQueryParam;
import com.datum.houserent.model.vo.core.PageQueryDTO;
import com.datum.houserent.service.HouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author datum
 * @since 2021-06-27
 */
@RestController
@Api(tags = "房屋 - 前端控制器")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @RequestMapping(value = "/common/houses",method = RequestMethod.GET)
    @ApiOperation("客户端分页分区查询房屋信息")
    public PageQueryDTO<HouseVO> pageHouse(HousePageQueryParam housePageQueryParam) {
        return null;
    }
}

