package com.datum.houserent.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.datum.houserent.exception.BadRequestException;
import com.datum.houserent.model.entity.House;
import com.datum.houserent.model.entity.Star;
import com.datum.houserent.model.vo.core.PageQueryDTO;
import com.datum.houserent.model.vo.house.HouseVO;
import com.datum.houserent.model.vo.house.StarHouse;
import com.datum.houserent.service.HouseService;
import com.datum.houserent.service.StarService;
import com.datum.houserent.support.ResponsePack;
import com.datum.houserent.utils.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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
@ResponsePack
@Api(tags = "收藏 - 前端控制器")
public class StarController {
    private final String ROLE_ADMIN = "admin";

    private final String ROLE_LESSOR = "lessor";

    private final String ROLE_USER = "user";

    private final StarService starService;

    private final HouseService houseService;

    public StarController(StarService starService, HouseService houseService) {
        this.starService = starService;
        this.houseService = houseService;
    }

    @SaCheckRole(value = {"admin","lessor","user"},mode = SaMode.OR)
    @ApiOperation("取消收藏（通过houseId）")
    @RequestMapping(value = "/common/star/house/{houseId:\\d+}", method = RequestMethod.DELETE)
    public boolean cancelStarByHouseId(@ApiParam("收藏id") @PathVariable Integer houseId) {
        Star star = starService.getOne(new LambdaQueryWrapper<Star>().eq(Star::getHouseId, houseId).eq(Star::getUserId, StpUtil.getLoginIdAsInt()));
        if (star == null) {
            throw new BadRequestException("收藏id不存在", "取消失败，刷新重试");
        }
        return starService.removeById(star.getId());

    }

    @SaCheckRole(value = {"admin","lessor","user"},mode = SaMode.OR)
    @ApiOperation("取消收藏（通过starId）")
    @RequestMapping(value = "/common/star/{starId:\\d+}", method = RequestMethod.DELETE)
    public boolean cancelStar(@ApiParam("收藏id") @PathVariable Integer starId) {
        Star star = starService.getById(starId);
        if (star == null) {
            throw new BadRequestException("收藏id不存在", "取消失败，刷新重试");
        }
        if (!star.getUserId().equals(StpUtil.getLoginIdAsInt()) && !StpUtil.hasRole(ROLE_ADMIN)) {
            throw new BadRequestException("此收藏不是该用户的收藏", "该收藏不属于该用户");
        }
        return starService.removeById(starId);
    }

    @SaCheckRole(value = {"admin","lessor","user"},mode = SaMode.OR)
    @ApiOperation("我的收藏")
    @RequestMapping(value = "/common/star", method = RequestMethod.GET)
    public List<StarHouse> myStars() {
        List<Star> stars = starService.list(new LambdaQueryWrapper<Star>().eq(Star::getUserId, StpUtil.getLoginIdAsInt()));
        if (stars.isEmpty()) {
            return Collections.emptyList();
        }
        List<Integer> houseIds = stars.stream().map(Star::getHouseId).collect(Collectors.toList());
        List<House> houses = houseService.list(new LambdaQueryWrapper<House>().in(House::getId, houseIds));
        if (houses.isEmpty()) {
            return Collections.emptyList();
        }
        for (House house : houses) {
            houseService.translateHouseUrl(house);
        }
        Map<Integer, HouseVO> houseVOMap = BeanUtil.convert(houses, HouseVO.class).stream().collect(Collectors.toMap(HouseVO::getId, Function.identity()));
        List<StarHouse> convert = BeanUtil.convert(stars, StarHouse.class);
        for (StarHouse starHouse : convert) {
            starHouse.setHouse(houseVOMap.get(starHouse.getHouseId()));
        }
        return convert;
    }

    @SaCheckRole(value = {"admin","lessor","user"},mode = SaMode.OR)
    @ApiOperation("收藏房源")
    @RequestMapping(value = "/common/star/{houseId:\\d+}", method = RequestMethod.PUT)
    public boolean starHouse(@ApiParam("房源id") @PathVariable Integer houseId) {
        House house = houseService.getById(houseId);
        if (house == null) {
            throw new BadRequestException("房源不存在", "该房源走丢了");
        }
        int userId = StpUtil.getLoginIdAsInt();
        Star temp = starService.getOne(new LambdaQueryWrapper<Star>().eq(Star::getHouseId, houseId).eq(Star::getUserId, userId));
        if (temp != null) {
            throw new BadRequestException("已经收藏过该房源", "不要重复收藏房源");
        }
        Star star = new Star();
        star.setHouseId(houseId);
        star.setUserId(userId);
        return starService.save(star);
    }
}

