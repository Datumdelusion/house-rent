package com.datum.houserent.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.datum.houserent.exception.BadRequestException;
import com.datum.houserent.exception.ServiceException;
import com.datum.houserent.model.entity.House;
import com.datum.houserent.model.entity.User;
import com.datum.houserent.model.entity.enums.HouseStatus;
import com.datum.houserent.model.vo.house.HouseDetailVO;
import com.datum.houserent.model.vo.house.HouseVO;
import com.datum.houserent.model.vo.core.HousePageQueryParam;
import com.datum.houserent.model.vo.core.PageQueryDTO;
import com.datum.houserent.model.vo.house.HouseDTO;
import com.datum.houserent.model.vo.user.Lessor;
import com.datum.houserent.service.HouseService;
import com.datum.houserent.service.UserService;
import com.datum.houserent.support.ResponsePack;
import com.datum.houserent.utils.BeanUtil;
import com.datum.houserent.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
@ResponsePack
public class HouseController {

    private final HouseService houseService;

    private final UserService userService;

    public HouseController(HouseService houseService, UserService userService) {
        this.houseService = houseService;
        this.userService = userService;
    }

    @RequestMapping(value = "/common/houses", method = RequestMethod.GET)
    @ApiOperation("客户端分页分区查询房屋信息")
    public PageQueryDTO<HouseVO> pageHouse(@ApiParam("条件参数") @Valid HousePageQueryParam housePageQueryParam) {
        LambdaQueryWrapper<House> wrapper = housePageQueryParam.queryWrapper().lambda().eq(House::getStatus, HouseStatus.OnShelf);
        return houseService.pageHouse(housePageQueryParam.page(), wrapper);
    }

    @RequestMapping(value = "/common/houses/detail/{houseId:\\d+}", method = RequestMethod.GET)
    @ApiOperation("房源详情")
    public HouseDetailVO getHouseDetail(@ApiParam("房源id") @PathVariable Integer houseId) {
        House house = houseService.getById(houseId);
        houseService.translateHouseUrl(house);
        if (house.getLessor() == null) {
            throw new ServiceException("房源出租者为null", "该房源暂时无人认领");
        }
        User user = userService.getById(house.getLessor());
        Lessor lessor = new Lessor().convertIn(user);
        HouseDetailVO detailVO = BeanUtil.convert(house, HouseDetailVO.class);
        try {
            detailVO.setPics(JsonUtil.parseObject(house.getPics(), String[].class));
            detailVO.setUsp(JsonUtil.parseObject(house.getUsp(), String[].class));
        } catch (JsonProcessingException e) {
            throw new ServiceException("字符串转对象错误", "系统错误，稍后再试");
        }
        detailVO.setLessor(lessor);
        return detailVO;
    }

    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @RequestMapping(value = "/lessor/houses/newHouse", method = RequestMethod.POST)
    @ApiOperation("新建房源")
    public boolean newHouse(@ApiParam("房屋信息") @RequestBody HouseDTO houseDTO) {
        House house = houseDTO.convertOut();
        house.setLessor(StpUtil.getLoginIdAsInt());

        try {
            house.setPics(JsonUtil.toJsonString(houseDTO.getPics()));
            house.setUsp(JsonUtil.toJsonString(houseDTO.getUsp()));
        } catch (JsonProcessingException e) {
            throw new BadRequestException("数组转字符串出错", "数组信息有误");
        }
        return houseService.save(house);
    }

    @SaCheckRole({"admin", "lessor"})
    @RequestMapping(value = "/lessor/houses/offShelf/{houseId:\\d+}", method = RequestMethod.PUT)
    @ApiOperation("下架房源")
    public boolean offShelf(@ApiParam("房源id") @PathVariable Integer houseId) {
        House house = houseService.getById(houseId);
        int lessor = StpUtil.getLoginIdAsInt();
        if (house.getLessor() != lessor) {
            throw new BadRequestException("非房源拥有者", "确认该房源属于当前账号？");
        }
        if (house.getStatus() == HouseStatus.OffShelf) {
            throw new BadRequestException("房源已是下架状态", "已经是下架状态了");
        }
        return houseService.update(new LambdaUpdateWrapper<House>()
                .eq(House::getId, houseId)
                .set(House::getStatus, HouseStatus.OffShelf));
    }

    @SaCheckRole({"admin", "lessor"})
    @RequestMapping(value = "/lessor/houses/onShelf/{houseId:\\d+}", method = RequestMethod.PUT)
    @ApiOperation("上架房源")
    public boolean onShelf(@ApiParam("房源id") @PathVariable Integer houseId) {
        House house = houseService.getById(houseId);
        int lessor = StpUtil.getLoginIdAsInt();
        if (house.getLessor() != lessor) {
            throw new BadRequestException("非房源拥有者", "确认该房源属于当前账号？");
        }
        if (house.getStatus() == HouseStatus.OnShelf) {
            throw new BadRequestException("房源已是上架状态", "已经是上架状态了");
        }
        return houseService.update(new LambdaUpdateWrapper<House>()
                .eq(House::getId, houseId)
                .set(House::getStatus, HouseStatus.OnShelf));
    }
}

