package com.example.anyanghome.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.anyanghome.common.Result;
import com.example.anyanghome.pojo.entity.AgriculturalPolicy;
import com.example.anyanghome.pojo.entity.AgriculturalPolicyCategory;
import com.example.anyanghome.service.AgriculturalPolicyCategoryService;
import com.example.anyanghome.service.AgriculturalPolicyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 助农政策控制器
 */
@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
@Tag(name = "助农政策", description = "助农政策相关接口")
public class AgriculturalPolicyController {

    private final AgriculturalPolicyCategoryService agriculturalPolicyCategoryService;
    private final AgriculturalPolicyService agriculturalPolicyService;

    @GetMapping("/categories")
    @Operation(summary = "获取助农政策分类列表")
    public Result<List<AgriculturalPolicyCategory>> getCategories() {
        List<AgriculturalPolicyCategory> categories = agriculturalPolicyCategoryService.getEnabledCategories();
        return Result.success(categories);
    }

    @GetMapping
    @Operation(summary = "获取助农政策列表")
    public Result<IPage<AgriculturalPolicy>> getAgriculturalPolicyList(
            @Parameter(description = "页码", example = "1") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "每页数量", example = "10") @RequestParam(defaultValue = "10") int pageSize,
            @Parameter(description = "分类ID") @RequestParam(required = false) Long categoryId) {
        IPage<AgriculturalPolicy> policyPage = agriculturalPolicyService.getAgriculturalPolicyPage(page, pageSize, categoryId);
        return Result.success(policyPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取助农政策详情")
    public Result<AgriculturalPolicy> getAgriculturalPolicyDetail(@Parameter(description = "政策ID") @PathVariable Long id) {
        AgriculturalPolicy policy = agriculturalPolicyService.getAgriculturalPolicyById(id);
        if (policy == null) {
            return Result.error("政策不存在或已下架");
        }
        // 增加阅读量
        agriculturalPolicyService.incrementViewCount(id);
        return Result.success(policy);
    }
}
