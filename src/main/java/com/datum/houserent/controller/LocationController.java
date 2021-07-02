package com.datum.houserent.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.datum.houserent.model.entity.Location;
import com.datum.houserent.model.vo.LocationVO;
import com.datum.houserent.service.LocationService;
import com.datum.houserent.support.ResponsePack;
import com.datum.houserent.utils.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author datum
 * @since 2021-06-27
 */
@Api(tags = "地理信息-控制器")
@RestController
@ResponsePack
public class LocationController {

    /**
     * 没有父id的省级地理信息
     */
    private final Integer NONE_PID = -1;

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @ApiOperation("获取某个地理信息id下的地理信息(id=-1返回顶级地理信息)")
    @GetMapping("/common/location")
    public List<LocationVO> getLocation(@ApiParam("地理位置id") @RequestParam Integer id) {

        List<Location> locations;
        if (id != null) {
            locations = locationService.list(new LambdaQueryWrapper<Location>().eq(Location::getPid, id).orderByAsc(Location::getPinyin));
        } else {
            locations = locationService.list(new LambdaQueryWrapper<Location>().eq(Location::getPid, NONE_PID).orderByAsc(Location::getPinyin));
        }
        return BeanUtil.convert(locations, LocationVO.class);
    }
}

