package com.datum.houserent.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.datum.houserent.model.entity.User;
import com.datum.houserent.support.ResponsePack;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author datum
 * @date 2021/6/22 21:30
 */
@Api(tags = "测试接口")
@RestController
@ResponsePack
public class TestController {

    @ApiOperation("接口登录测试")
    @RequestMapping(value = "/common/hello", method = RequestMethod.GET)
    public User hello() {

        StpUtil.login(100);
        System.out.println(StpUtil.getTokenInfo());
        return new User();
    }

    @ApiOperation("接口测试")
    @SaCheckRole("admin")
    @RequestMapping(value = "/common/satoken", method = RequestMethod.GET)
    public User satoken() {
        System.out.println(StpUtil.isLogin());
        return new User();
    }
}
