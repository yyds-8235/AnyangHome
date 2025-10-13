package com.example.anyanghome.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.anyanghome.pojo.entity.AgriculturalPolicy;

/**
 * 助农政策服务接口
 */
public interface AgriculturalPolicyService {

    /**
     * 分页查询助农政策列表
     */
    IPage<AgriculturalPolicy> getAgriculturalPolicyPage(int page, int pageSize, Long categoryId);

    /**
     * 根据ID获取助农政策详情
     */
    AgriculturalPolicy getAgriculturalPolicyById(Long id);

    /**
     * 增加助农政策阅读量
     */
    void incrementViewCount(Long id);
}
