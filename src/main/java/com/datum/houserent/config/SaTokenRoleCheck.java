package com.datum.houserent.config;

import cn.dev33.satoken.stp.StpInterface;
import com.datum.houserent.exception.BadRequestException;
import com.datum.houserent.model.entity.User;
import com.datum.houserent.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author datum
 * @date 2021/6/26 21:28
 */
@Component
public class SaTokenRoleCheck implements StpInterface {

    private final UserService userService;

    public SaTokenRoleCheck(UserService userService) {
        this.userService = userService;
    }

    private final List<String> user = new ArrayList<String>() {{
            add("user");
        }};
    private final List<String> lessor = new ArrayList<String>() {{
        add("lessor");
    }};
    private final List<String> userLessor = new ArrayList<String>() {{
        add("user");
        add("lessor");
    }};
    private final List<String> admin = new ArrayList<String>() {{
        add("admin");
    }};

    /**
     * 返回指定 LoginId 所拥有的权限码集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    /**
     * 返回指定loginId所拥有的角色标识集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Integer type = userService.getById(Integer.parseInt((String) loginId)).getUserType();
        if (type == 0) {
            return admin;
        }
        if (type == 1) {
            return user;
        }
        if (type == 2) {
            return lessor;
        }
        if (type == 3) {
            return userLessor;
        }
        throw new BadRequestException("没有权限类型", "没有登录权限");
    }
}
