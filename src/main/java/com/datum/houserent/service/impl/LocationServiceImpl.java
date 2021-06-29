package com.datum.houserent.service.impl;

import com.datum.houserent.model.entity.Location;
import com.datum.houserent.dao.mapper.LocationMapper;
import com.datum.houserent.service.LocationService;
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
public class LocationServiceImpl extends BaseServiceImpl<LocationMapper, Location> implements LocationService {

}
