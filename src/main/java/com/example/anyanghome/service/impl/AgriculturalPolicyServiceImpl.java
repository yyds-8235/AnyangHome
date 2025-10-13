package com.example.anyanghome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.anyanghome.mapper.AgriculturalPolicyMapper;
import com.example.anyanghome.pojo.entity.AgriculturalPolicy;
import com.example.anyanghome.service.AgriculturalPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 助农政策服务实现类
 */
@Service
@RequiredArgsConstructor
public class AgriculturalPolicyServiceImpl implements AgriculturalPolicyService {

    private final AgriculturalPolicyMapper agriculturalPolicyMapper;

    @Override
    public IPage<AgriculturalPolicy> getAgriculturalPolicyPage(int page, int pageSize, Long categoryId) {
        Page<AgriculturalPolicy> pageParam = new Page<>(page, pageSize);
        return agriculturalPolicyMapper.selectAgriculturalPolicyPage(pageParam, categoryId);
    }

    @Override
    public AgriculturalPolicy getAgriculturalPolicyById(Long id) {
        LambdaQueryWrapper<AgriculturalPolicy> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AgriculturalPolicy::getId, id)
                   .eq(AgriculturalPolicy::getIsEnabled, 1);
        return agriculturalPolicyMapper.selectOne(queryWrapper);
    }

    @Override
    public void incrementViewCount(Long id) {
        AgriculturalPolicy policy = agriculturalPolicyMapper.selectById(id);
        if (policy != null) {
            policy.setViewCount(policy.getViewCount() + 1);
            agriculturalPolicyMapper.updateById(policy);
        }
    }
}
