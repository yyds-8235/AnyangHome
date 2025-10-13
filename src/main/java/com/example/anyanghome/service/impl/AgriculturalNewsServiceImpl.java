package com.example.anyanghome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.anyanghome.mapper.AgriculturalNewsMapper;
import com.example.anyanghome.pojo.entity.AgriculturalNews;
import com.example.anyanghome.service.AgriculturalNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 助农资讯服务实现类
 */
@Service
@RequiredArgsConstructor
public class AgriculturalNewsServiceImpl implements AgriculturalNewsService {

    private final AgriculturalNewsMapper agriculturalNewsMapper;

    @Override
    public IPage<AgriculturalNews> getAgriculturalNewsPage(int page, int pageSize, String category) {
        Page<AgriculturalNews> pageParam = new Page<>(page, pageSize);
        return agriculturalNewsMapper.selectAgriculturalNewsPage(pageParam, category);
    }

    @Override
    public AgriculturalNews getAgriculturalNewsById(Long id) {
        LambdaQueryWrapper<AgriculturalNews> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AgriculturalNews::getId, id)
                   .eq(AgriculturalNews::getIsEnabled, 1);
        return agriculturalNewsMapper.selectOne(queryWrapper);
    }

    @Override
    public void incrementViewCount(Long id) {
        AgriculturalNews news = agriculturalNewsMapper.selectById(id);
        if (news != null) {
            news.setViewCount(news.getViewCount() + 1);
            agriculturalNewsMapper.updateById(news);
        }
    }
}
