package com.datum.houserent.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.datum.houserent.model.entity.House;
import com.datum.houserent.model.vo.house.HouseVO;
import com.datum.houserent.model.vo.core.PageQueryDTO;
import com.datum.houserent.service.core.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author datum
 * @since 2021-06-27
 */
public interface HouseService extends BaseService<House> {

    /**
     * 条件分页查询
     * @param page 分页条件
     * @param queryWrapper 查询条件
     * @return 返回分页内容
     */
    PageQueryDTO<HouseVO> pageHouse(Page<House> page, LambdaQueryWrapper<House> queryWrapper);

    /**
     * 转换房屋的链接为可用链接
     * @param house 房屋
     */
    void translateHouseUrl(House house);

}
