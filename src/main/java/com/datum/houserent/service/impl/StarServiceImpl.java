package com.datum.houserent.service.impl;

import com.datum.houserent.model.entity.Star;
import com.datum.houserent.dao.mapper.StarMapper;
import com.datum.houserent.service.StarService;
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
public class StarServiceImpl extends BaseServiceImpl<StarMapper, Star> implements StarService {

}
