package com.example.anyanghome.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.anyanghome.pojo.entity.AgriculturalProduct;

/**
 * 助农产品服务接口
 */
public interface AgriculturalProductService {

    /**
     * 分页查询助农产品列表
     */
    IPage<AgriculturalProduct> getAgriculturalProductPage(int page, int pageSize);

    /**
     * 根据ID获取助农产品详情
     */
    AgriculturalProduct getAgriculturalProductById(Long id);
}
