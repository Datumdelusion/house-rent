package com.datum.houserent.service.impl;

import com.datum.houserent.model.entity.House;
import com.datum.houserent.dao.mapper.HouseMapper;
import com.datum.houserent.service.HouseService;
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
public class HouseServiceImpl extends BaseServiceImpl<HouseMapper, House> implements HouseService {

}
