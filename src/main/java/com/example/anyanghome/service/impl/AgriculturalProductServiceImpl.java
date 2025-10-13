package com.example.anyanghome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.anyanghome.mapper.AgriculturalProductMapper;
import com.example.anyanghome.pojo.entity.AgriculturalProduct;
import com.example.anyanghome.service.AgriculturalProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 助农产品服务实现类
 */
@Service
@RequiredArgsConstructor
public class AgriculturalProductServiceImpl implements AgriculturalProductService {

    private final AgriculturalProductMapper agriculturalProductMapper;

    @Override
    public IPage<AgriculturalProduct> getAgriculturalProductPage(int page, int pageSize) {
        Page<AgriculturalProduct> pageParam = new Page<>(page, pageSize);
        return agriculturalProductMapper.selectAgriculturalProductPage(pageParam);
    }

    @Override
    public AgriculturalProduct getAgriculturalProductById(Long id) {
        LambdaQueryWrapper<AgriculturalProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AgriculturalProduct::getId, id)
                   .eq(AgriculturalProduct::getIsEnabled, 1);
        return agriculturalProductMapper.selectOne(queryWrapper);
    }
}
