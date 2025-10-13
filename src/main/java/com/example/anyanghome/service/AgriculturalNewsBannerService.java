package com.example.anyanghome.service;

import com.example.anyanghome.pojo.entity.AgriculturalNewsBanner;

import java.util.List;

/**
 * 助农资讯轮播图服务接口
 */
public interface AgriculturalNewsBannerService {

    /**
     * 获取所有启用的轮播图
     */
    List<AgriculturalNewsBanner> getEnabledBanners();
}
