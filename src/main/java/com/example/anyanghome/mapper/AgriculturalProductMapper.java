package com.example.anyanghome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.anyanghome.pojo.entity.AgriculturalProduct;
import org.apache.ibatis.annotations.Mapper;

/**
 * 助农产品Mapper接口
 */
@Mapper
public interface AgriculturalProductMapper extends BaseMapper<AgriculturalProduct> {

    /**
     * 分页查询助农产品列表
     */
    IPage<AgriculturalProduct> selectAgriculturalProductPage(Page<AgriculturalProduct> page);
}