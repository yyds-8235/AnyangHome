package com.example.anyanghome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.anyanghome.pojo.entity.AgriculturalPolicy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 助农政策Mapper接口
 */
@Mapper
public interface AgriculturalPolicyMapper extends BaseMapper<AgriculturalPolicy> {

    /**
     * 分页查询助农政策列表
     */
    IPage<AgriculturalPolicy> selectAgriculturalPolicyPage(Page<AgriculturalPolicy> page, @Param("categoryId") Long categoryId);
}
