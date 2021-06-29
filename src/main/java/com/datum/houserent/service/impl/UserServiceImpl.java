package com.datum.houserent.service.impl;

import com.datum.houserent.model.entity.User;
import com.datum.houserent.dao.mapper.UserMapper;
import com.datum.houserent.service.UserService;
import com.datum.houserent.service.core.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author datum
 * @since 2021-06-27
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

}
