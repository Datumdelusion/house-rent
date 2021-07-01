package com.datum.houserent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.datum.houserent.model.entity.House;
import com.datum.houserent.dao.mapper.HouseMapper;
import com.datum.houserent.model.vo.house.HouseVO;
import com.datum.houserent.model.vo.core.PageQueryDTO;
import com.datum.houserent.service.HouseService;
import com.datum.houserent.service.core.BaseServiceImpl;
import com.datum.houserent.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author datum
 * @since 2021-06-27
 */
@Service
public class HouseServiceImpl extends BaseServiceImpl<HouseMapper, House> implements HouseService {

    private final String URL_PREF = "https://imgpro.ziroom.com/";
    private final String URL_HEAD_SUFFIX = ".jpg_C_400_300_Q100.jpg";
    private final String URL_SUFFIX = ".jpg_C_800_600_Q100.jpg";
    /**
     * 条件分页查询
     *
     * @param page         分页条件
     * @param queryWrapper 查询条件
     * @return 返回分页内容
     */
    @Override
    public PageQueryDTO<HouseVO> pageHouse(Page<House> page, LambdaQueryWrapper<House> queryWrapper) {
        Assert.notNull(page, "分页查询page参数不能为null");
        Assert.notNull(queryWrapper, "分页查询的Wrapper不能为null");

        Page<House> housePage = page(page, queryWrapper);
        List<House> records = housePage.getRecords();
        for (House house : records) {
            translateHouseUrl(house);
        }
        return PageQueryDTO.convertIn(housePage, HouseVO.class);
    }

    /**
     * 转换房屋的链接为可用链接
     *
     * @param house 房屋
     */
    @Override
    public void translateHouseUrl(House house) {
        Assert.notNull(house, "房屋实例不能为空");
        if (house.getHead().contains("http")) {
            return;
        } else {
            String head = house.getHead();
            head = URL_PREF + head + URL_HEAD_SUFFIX;
            house.setHead(head);
        }
        if (house.getPics().contains("http")) {
            return;
        } else {
            String pics = house.getPics();
            try {
                ArrayList<String> arrayList = JsonUtil.parseObject(pics, ArrayList.class);
                List<String> list = new ArrayList<>();
                for (String url : arrayList) {
                    url = URL_PREF + url + URL_SUFFIX;
                    list.add(url);
                }
                house.setPics(JsonUtil.toJsonString(list));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
