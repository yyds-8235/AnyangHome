package com.example.anyanghome.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.anyanghome.pojo.entity.AgriculturalNews;

/**
 * 助农资讯服务接口
 */
public interface AgriculturalNewsService {

    /**
     * 分页查询助农资讯列表
     */
    IPage<AgriculturalNews> getAgriculturalNewsPage(int page, int pageSize, String category);

    /**
     * 根据ID获取助农资讯详情
     */
    AgriculturalNews getAgriculturalNewsById(Long id);

    /**
     * 增加助农资讯阅读量
     */
    void incrementViewCount(Long id);
}
