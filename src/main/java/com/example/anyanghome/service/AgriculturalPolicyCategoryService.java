package com.example.anyanghome.service;

import com.example.anyanghome.pojo.entity.AgriculturalPolicyCategory;

import java.util.List;

/**
 * 助农政策分类服务接口
 */
public interface AgriculturalPolicyCategoryService {

    /**
     * 获取所有启用的助农政策分类
     */
    List<AgriculturalPolicyCategory> getEnabledCategories();
}
