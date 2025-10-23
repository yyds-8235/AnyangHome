package com.example.anyanghome.service;

import com.example.anyanghome.pojo.entity.BusRoute;

import java.util.List;

/**
 * 客车路线服务接口
 */
public interface BusRouteService {

    /**
     * 获取所有启用的客车路线
     */
    List<BusRoute> getAvailableRoutes();

    /**
     * 根据ID获取路线详情
     */
    BusRoute getRouteById(Long id);
}
