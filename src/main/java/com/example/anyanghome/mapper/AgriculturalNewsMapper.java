package com.example.anyanghome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.anyanghome.pojo.entity.AgriculturalNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 助农资讯Mapper接口
 */
@Mapper
public interface AgriculturalNewsMapper extends BaseMapper<AgriculturalNews> {

    /**
     * 分页查询助农资讯列表
     */
    IPage<AgriculturalNews> selectAgriculturalNewsPage(Page<AgriculturalNews> page, @Param("category") String category);
}
