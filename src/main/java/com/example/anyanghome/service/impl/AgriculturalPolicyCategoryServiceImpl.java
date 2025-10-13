package com.example.anyanghome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.anyanghome.mapper.AgriculturalPolicyCategoryMapper;
import com.example.anyanghome.pojo.entity.AgriculturalPolicyCategory;
import com.example.anyanghome.service.AgriculturalPolicyCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 助农政策分类服务实现类
 */
@Service
@RequiredArgsConstructor
public class AgriculturalPolicyCategoryServiceImpl implements AgriculturalPolicyCategoryService {

    private final AgriculturalPolicyCategoryMapper agriculturalPolicyCategoryMapper;

    @Override
    public List<AgriculturalPolicyCategory> getEnabledCategories() {
        LambdaQueryWrapper<AgriculturalPolicyCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AgriculturalPolicyCategory::getIsEnabled, 1)
                   .orderByAsc(AgriculturalPolicyCategory::getSortOrder);
        return agriculturalPolicyCategoryMapper.selectList(queryWrapper);
    }
}
