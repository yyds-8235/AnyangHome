package com.example.anyanghome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.anyanghome.mapper.BusRouteMapper;
import com.example.anyanghome.pojo.entity.BusRoute;
import com.example.anyanghome.service.BusRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客车路线服务实现类
 */
@Service
@RequiredArgsConstructor
public class BusRouteServiceImpl implements BusRouteService {

    private final BusRouteMapper busRouteMapper;

    @Override
    public List<BusRoute> getAvailableRoutes() {
        LambdaQueryWrapper<BusRoute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BusRoute::getIsEnabled, 1)
                .gt(BusRoute::getAvailableSeats, 0)
                .orderByAsc(BusRoute::getDepartureTime);
        return busRouteMapper.selectList(queryWrapper);
    }

    @Override
    public BusRoute getRouteById(Long id) {
        return busRouteMapper.selectById(id);
    }
}
