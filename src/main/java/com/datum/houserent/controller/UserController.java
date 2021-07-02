package com.datum.houserent.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.datum.houserent.exception.BadRequestException;
import com.datum.houserent.model.entity.User;
import com.datum.houserent.model.vo.token.Token;
import com.datum.houserent.model.vo.user.LoginBody;
import com.datum.houserent.model.vo.user.UserInfo;
import com.datum.houserent.service.UserService;
import com.datum.houserent.support.ResponsePack;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.util.DigestUtils;
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
@Api(tags = "用户 - 前端控制器")
@RestController
@ResponsePack
public class UserController {

    private final String SALT = "b58ec155a97f46878f120c41ed14f5d5";

    private final WxMaService wxMaService;
    private final UserService userService;

    public UserController(WxMaService wxMaService, UserService userService) {
        this.wxMaService = wxMaService;
        this.userService = userService;
    }

    @RequestMapping(value = "/common/user/login", method = RequestMethod.GET)
    @ApiOperation("（已弃用）使用微信code登录")
    public Token login(@ApiParam("code") @RequestParam String code) {
        User user;
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            String openid = session.getOpenid();
            user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getOpenId, openid));
        } catch (WxErrorException e) {
            throw new BadRequestException(e.getMessage(), "微信错误");
        }

        StpUtil.login(user.getId());
        return new Token(StpUtil.getTokenValue());
    }

    @RequestMapping(value = "/common/user/userInfo", method = RequestMethod.GET)
    @ApiOperation("（已弃用）获取用户头像昵称信息")
    public UserInfo getUserInfo() {
        Integer loginId = (Integer) StpUtil.getLoginId();
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, loginId));

        return new UserInfo();
    }

    @RequestMapping(value = "/common/login", method = RequestMethod.GET)
    @ApiOperation("测试登录")
    public Token testLogin(@RequestParam Integer loginId) {
        StpUtil.login(loginId);
        return new Token(StpUtil.getTokenValue());
    }

    @ApiOperation("用户账号密码登录")
    @RequestMapping(value = "/common/login", method = RequestMethod.POST)
    public Token loginByPassword(@Valid @RequestBody LoginBody loginBody) {
        String md5Password = DigestUtils.md5DigestAsHex((SALT + loginBody.getPassword()).getBytes());
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getName, loginBody.getName()).select(User::getId, User::getPassword));
        if (user == null) {
            throw new BadRequestException("用户不存在", "账号或者密码错误");
        }
        if (!md5Password.equals(user.getPassword())) {
            throw new BadRequestException("密码错误", "账号或密码错误");
        }
        StpUtil.login(user.getId());

        return new Token(StpUtil.getTokenValue());
    }
}

