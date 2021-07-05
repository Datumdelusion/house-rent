package com.datum.houserent.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.datum.houserent.model.entity.House;
import com.datum.houserent.model.entity.Intention;
import com.datum.houserent.model.entity.User;
import com.datum.houserent.model.vo.core.IntentionVO;
import com.datum.houserent.model.vo.house.HouseVO;
import com.datum.houserent.model.vo.user.UserVO;
import com.datum.houserent.service.HouseService;
import com.datum.houserent.service.IntentionService;
import com.datum.houserent.service.UserService;
import com.datum.houserent.support.ResponsePack;
import com.datum.houserent.utils.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.bouncycastle.its.asn1.IValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@Api(tags = "用户意向 - 前端控制器")
public class IntentionController {

    private final IntentionService intentionService;
    private final HouseService houseService;
    private final UserService userService;

    public IntentionController(IntentionService intentionService, HouseService houseService, UserService userService) {
        this.intentionService = intentionService;
        this.houseService = houseService;
        this.userService = userService;
    }

    @SaCheckRole(value = {"user", "admin"}, mode = SaMode.OR)
    @ApiOperation("（意向租房）交换出租方联系方式")
    @RequestMapping(value = "/user/intention/{houseId:\\d+}", method = RequestMethod.GET)
    public boolean getLessorMessage(@ApiParam("房源id") @PathVariable Integer houseId) {
        Intention intention = intentionService.getOne(new LambdaQueryWrapper<Intention>().eq(Intention::getHouseId, houseId).eq(Intention::getUserId, StpUtil.getLoginIdAsInt()));

        if (intention != null) {
            return true;
        }
        House house = houseService.getById(houseId);
        intention = new Intention();
        intention.setHouseId(houseId);
        intention.setUserId(StpUtil.getLoginIdAsInt());
        intention.setLessor(house.getLessor());
        return intentionService.save(intention);
    }

    @SaCheckRole(value = {"admin", "lessor"}, mode = SaMode.OR)
    @ApiOperation("我的房源意向")
    @RequestMapping(value = "/lessor/intention", method = RequestMethod.GET)
    public List<IntentionVO> getIntention() {
        int lessor = StpUtil.getLoginIdAsInt();

        List<Intention> intentions = intentionService.list(new LambdaQueryWrapper<Intention>().eq(Intention::getLessor, lessor));
        if (intentions.isEmpty()) {
            return Collections.emptyList();
        }
        List<Integer> userIds = intentions.stream().map(Intention::getUserId).collect(Collectors.toList());
        List<Integer> houseIds = intentions.stream().map(Intention::getHouseId).collect(Collectors.toList());
        Map<Integer, User> userMap = userService.list(new LambdaQueryWrapper<User>().in(User::getId, userIds)).stream().collect(Collectors.toMap(User::getId, Function.identity()));
        Map<Integer, House> houseMap = houseService.list(new LambdaQueryWrapper<House>().in(House::getId, houseIds)).stream().collect(Collectors.toMap(House::getId, Function.identity()));

        List<IntentionVO> intentionVOs = new ArrayList<>();
        for (Intention intention : intentions) {
            IntentionVO intentionVO = new IntentionVO();
            intentionVO.setId(intention.getId());
            House house = houseMap.get(intention.getHouseId());
            if (house == null) {
                continue;
            }
            houseService.translateHouseUrl(house);
            intentionVO.setHouse(BeanUtil.convert(house, HouseVO.class));
            User user = userMap.get(intention.getUserId());
            intentionVO.setUser(BeanUtil.convert(user, UserVO.class));
            intentionVO.setLessor(lessor);
            intentionVOs.add(intentionVO);
        }
        return intentionVOs;
    }
}

