package com.example.anyanghome.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.anyanghome.common.Result;
import com.example.anyanghome.pojo.entity.AgriculturalNews;
import com.example.anyanghome.pojo.entity.AgriculturalNewsBanner;
import com.example.anyanghome.service.AgriculturalNewsBannerService;
import com.example.anyanghome.service.AgriculturalNewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 助农资讯控制器
 */
@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
@Tag(name = "助农资讯", description = "助农资讯相关接口")
public class AgriculturalNewsController {

    private final AgriculturalNewsBannerService agriculturalNewsBannerService;
    private final AgriculturalNewsService agriculturalNewsService;

    @GetMapping("/banners")
    @Operation(summary = "获取助农资讯轮播图")
    public Result<List<AgriculturalNewsBanner>> getBanners() {
        List<AgriculturalNewsBanner> banners = agriculturalNewsBannerService.getEnabledBanners();
        return Result.success(banners);
    }

    @GetMapping
    @Operation(summary = "获取助农资讯列表")
    public Result<IPage<AgriculturalNews>> getAgriculturalNewsList(
            @Parameter(description = "页码", example = "1") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "每页数量", example = "10") @RequestParam(defaultValue = "10") int pageSize,
            @Parameter(description = "资讯分类") @RequestParam(required = false) String category) {
        IPage<AgriculturalNews> newsPage = agriculturalNewsService.getAgriculturalNewsPage(page, pageSize, category);
        return Result.success(newsPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取助农资讯详情")
    public Result<AgriculturalNews> getAgriculturalNewsDetail(@Parameter(description = "资讯ID") @PathVariable Long id) {
        AgriculturalNews news = agriculturalNewsService.getAgriculturalNewsById(id);
        if (news == null) {
            return Result.error("资讯不存在或已下架");
        }
        // 增加阅读量
        agriculturalNewsService.incrementViewCount(id);
        return Result.success(news);
    }

    @PostMapping("/{id}/like")
    @Operation(summary = "点赞助农资讯")
    public Result<Void> likeAgriculturalNews(@Parameter(description = "资讯ID") @PathVariable Long id) {
        agriculturalNewsService.likeAgriculturalNews(id);
        return Result.success();
    }
}
