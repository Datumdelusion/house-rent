package com.datum.houserent.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.datum.houserent.exception.BadRequestException;
import com.datum.houserent.exception.ServiceException;
import com.datum.houserent.model.entity.*;
import com.datum.houserent.model.entity.enums.HouseStatus;
import com.datum.houserent.model.vo.house.HouseDetailVO;
import com.datum.houserent.model.vo.house.HouseVO;
import com.datum.houserent.model.vo.queryCondition.HousePageQueryParam;
import com.datum.houserent.model.vo.core.PageQueryDTO;
import com.datum.houserent.model.vo.house.HouseDTO;
import com.datum.houserent.model.vo.user.UserVO;
import com.datum.houserent.service.*;
import com.datum.houserent.support.ResponsePack;
import com.datum.houserent.utils.BeanUtil;
import com.datum.houserent.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    private final String ROLE_ADMIN = "admin";

    private final String ROLE_LESSOR = "lessor";

    private final String ROLE_USER = "user";

    private final HouseService houseService;

    private final UserService userService;

    private final ContractService contractService;

    private final LocationService locationService;

    private final StarService starService;

    public HouseController(HouseService houseService, UserService userService,
                           ContractService contractService, LocationService locationService, StarService starService) {
        this.houseService = houseService;
        this.userService = userService;
        this.contractService = contractService;
        this.locationService = locationService;
        this.starService = starService;
    }

    @RequestMapping(value = "/common/houses", method = RequestMethod.GET)
    @ApiOperation("客户端分页分区查询房屋信息")
    public PageQueryDTO<HouseVO> pageHouse(@ApiParam("条件参数") @Valid HousePageQueryParam housePageQueryParam) {
        LambdaQueryWrapper<House> wrapper = housePageQueryParam.queryWrapper().lambda().eq(House::getStatus, HouseStatus.OnShelf);
        if (housePageQueryParam.getLocationThree() == null && housePageQueryParam.getLocationName() != null) {
            Location location = locationService.getOne(new LambdaQueryWrapper<Location>().eq(Location::getName, housePageQueryParam.getLocationName()));
            if (location != null) {
                wrapper.eq(House::getLocationThree, location.getId());
            }
        }

        return houseService.pageHouse(housePageQueryParam.page(), wrapper);
    }

    @RequestMapping(value = "/common/houses/detail/{houseId:\\d+}", method = RequestMethod.GET)
    @ApiOperation("房源详情")
    public HouseDetailVO getHouseDetail(@ApiParam("房源id") @PathVariable Integer houseId) {
        House house = houseService.getById(houseId);
        if (house == null) {
            throw new BadRequestException("房源不存在", "该房源走丢了");
        }
        houseService.translateHouseUrl(house);
        if (house.getLessor() == null) {
            throw new ServiceException("房源出租者为null", "该房源暂时无人认领");
        }
        User user = userService.getById(house.getLessor());
        UserVO lessor = new UserVO().convertIn(user);
        HouseDetailVO detailVO = BeanUtil.convert(house, HouseDetailVO.class);
        try {
            detailVO.setPics(JsonUtil.parseObject(house.getPics(), String[].class));
            detailVO.setUsp(JsonUtil.parseObject(house.getUsp(), String[].class));
        } catch (JsonProcessingException e) {
            throw new ServiceException("字符串转对象错误", "系统错误，稍后再试");
        }
        List<Integer> locals = new ArrayList<>();
        locals.add(house.getLocationOne());
        locals.add(house.getLocationTwo());
        locals.add(house.getLocationThree());
        Map<Integer, Location> locationMap = locationService.list(new LambdaQueryWrapper<Location>()
                .in(Location::getId, locals))
                .stream()
                .collect(Collectors
                        .toMap(Location::getId, Function.identity()));

        detailVO.setLocationOneName(locationMap.get(house.getLocationOne()).getName());
        detailVO.setLocationTwoName(locationMap.get(house.getLocationTwo()).getName());
        detailVO.setLocationThreeName(locationMap.get(house.getLocationThree()).getName());
        detailVO.setLessor(lessor);
        detailVO.setStar(false);
        if (StpUtil.isLogin()) {
            Star star = starService.getOne(new LambdaQueryWrapper<Star>().eq(Star::getUserId, StpUtil.getLoginIdAsInt()).eq(Star::getHouseId, houseId));
            detailVO.setStar(star != null);
        }
        houseService.somebodyVisited(houseId);
        return detailVO;
    }

    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @RequestMapping(value = "/lessor/houses/newHouse", method = RequestMethod.POST)
    @ApiOperation("新建房源")
    public boolean newHouse(@ApiParam("房屋信息") @Valid @RequestBody HouseDTO houseDTO) {
        House house = houseDTO.convertOut();
        house.setLessor(StpUtil.getLoginIdAsInt());
        try {
            house.setPics(JsonUtil.toJsonString(houseDTO.getPics()));
            house.setUsp(JsonUtil.toJsonString(houseDTO.getUsp()));
        } catch (JsonProcessingException e) {
            throw new BadRequestException("数组转字符串出错", "数组信息有误");
        }
        String locationThreeName = houseDTO.getLocationThreeName();
        Location location = locationService.getOne(new LambdaQueryWrapper<Location>().eq(Location::getName, locationThreeName));
        if (location == null) {
            throw new BadRequestException("地理位置不存在" + locationThreeName, "地理位置" + locationThreeName + "不存在");
        }
        house.setLocationThree(location.getId());
        return houseService.save(house);
    }

    @ApiOperation("删除房源")
    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @RequestMapping(value = "/lessor/houses/deleteHouse/{houseId:\\d+}", method = RequestMethod.DELETE)
    public boolean deleteHouse(@ApiParam("房源id") @PathVariable Integer houseId) {
        House house = houseService.getById(houseId);
        if (house == null) {
            throw new BadRequestException("房源不存在", "该房源走丢了");
        }
        System.out.println(StpUtil.getLoginIdAsInt());
        System.out.println(StpUtil.hasRole(ROLE_ADMIN));
        if (!house.getLessor().equals(StpUtil.getLoginIdAsInt()) && !StpUtil.hasRole(ROLE_ADMIN)) {
            throw new BadRequestException("不是房源拥有者", "不是房源拥有者");
        }
        if (house.getStatus().equals(HouseStatus.OffShelf)) {
            Contract contract = contractService.getOne(new LambdaQueryWrapper<Contract>().eq(Contract::getHouseId, houseId));
            LocalDate time = contract.getTime();
            LocalDate now = LocalDate.now();
            if (now.isBefore(time)) {
                throw new BadRequestException("房源已出租，无法删除", "房源处于租用期间，无法删除");
            }
            return houseService.removeById(houseId);
        }
        return houseService.removeById(houseId);
    }

    @ApiOperation("修改房源")
    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @RequestMapping(value = "/lessor/houses/editHouse/{houseId:\\d+}", method = RequestMethod.POST)
    public boolean editHouse(@ApiParam("房源id") @PathVariable Integer houseId,
                             @RequestBody HouseDTO houseDTO) {
        House house = houseService.getById(houseId);
        if (house == null) {
            throw new BadRequestException("房源不存在", "该房源走丢了");
        }
        if (!house.getLessor().equals(StpUtil.getLoginIdAsInt()) && !StpUtil.hasRole(ROLE_ADMIN)) {
            throw new BadRequestException("不是房源拥有者", "不是房源拥有者");
        }
        House convertOut = houseDTO.convertOut();
        convertOut.setId(houseId);
        try {
            if (houseDTO.getPics() != null) {
                convertOut.setPics(JsonUtil.toJsonString(houseDTO.getPics()));
            }
            if (houseDTO.getUsp() != null) {
                convertOut.setUsp(JsonUtil.toJsonString(houseDTO.getUsp()));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String locationThreeName = houseDTO.getLocationThreeName();
        if (locationThreeName != null) {
            Location location = locationService.getOne(new LambdaQueryWrapper<Location>().eq(Location::getName, locationThreeName));
            if (location == null) {
                throw new BadRequestException("地理位置不存在" + locationThreeName, "地理位置" + locationThreeName + "不存在");
            }
            convertOut.setLocationThree(location.getId());
        }
        return houseService.updateById(convertOut);
    }

    @ApiOperation("将房屋状态改为上架招租")
    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @RequestMapping(value = "/lessor/house/onShelf/{houseId:\\d+}", method = RequestMethod.PUT)
    public boolean onShelfHouse(@ApiParam("房源id") @PathVariable Integer houseId) {
        House house = houseService.getById(houseId);

        if (house == null) {
            throw new BadRequestException("房源id不存在", "刷新重试，请确认房源还存在");
        }
        if (!house.getLessor().equals(StpUtil.getLoginIdAsInt())) {
            throw new BadRequestException("当前房源不属于操作者", "房源不属于当前操作者");
        }
        if (house.getStatus().equals(HouseStatus.OnShelf)) {
            throw new BadRequestException("房源已经处于招租状态", "房源已上架，切勿重复操作");
        }
        Contract contract = contractService.getOne(new LambdaQueryWrapper<Contract>().eq(Contract::getHouseId, houseId).eq(Contract::getSuccess, 1));
        LocalDate date = contract.getTime();
        LocalDate now = LocalDate.now();
        if (now.isBefore(date)) {
            throw new BadRequestException("房源正处于租赁状态", "房屋有未到期合约");
        }
        House houseSave = new House();
        houseSave.setId(houseId);
        houseSave.setStatus(HouseStatus.OnShelf);
        return houseService.updateById(houseSave);
    }

    @ApiOperation("我的房源")
    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @RequestMapping(value = "/lessor/houses", method = RequestMethod.GET)
    public List<HouseVO> myHouses() {
        int lessor = StpUtil.getLoginIdAsInt();
        List<House> houses = houseService.list(new LambdaQueryWrapper<House>().eq(House::getLessor, lessor));
        if (houses.isEmpty()) {
            return Collections.emptyList();
        }
        for (House house : houses) {
            houseService.translateHouseUrl(house);
        }
        return BeanUtil.convert(houses, HouseVO.class);
    }
}

