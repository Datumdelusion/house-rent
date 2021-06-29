package com.datum.houserent.config;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author datum
 * @date 2021/6/26 21:28
 */
@Component
public class SaTokenRoleCheck implements StpInterface {
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

        //通过loginid查找usertype
        //分配权限
        List<String> list = new ArrayList<>();
        list.add("admin");
        return list;
    }
}
