package com.example.anyanghome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.anyanghome.mapper.AgriculturalNewsBannerMapper;
import com.example.anyanghome.pojo.entity.AgriculturalNewsBanner;
import com.example.anyanghome.service.AgriculturalNewsBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 助农资讯轮播图服务实现类
 */
@Service
@RequiredArgsConstructor
public class AgriculturalNewsBannerServiceImpl implements AgriculturalNewsBannerService {

    private final AgriculturalNewsBannerMapper agriculturalNewsBannerMapper;

    @Override
    public List<AgriculturalNewsBanner> getEnabledBanners() {
        LambdaQueryWrapper<AgriculturalNewsBanner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AgriculturalNewsBanner::getIsEnabled, 1)
                   .orderByAsc(AgriculturalNewsBanner::getSortOrder);
        return agriculturalNewsBannerMapper.selectList(queryWrapper);
    }
}
