package com.example.anyanghome.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.anyanghome.common.Result;
import com.example.anyanghome.pojo.entity.AgriculturalProduct;
import com.example.anyanghome.service.AgriculturalProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 助农产品控制器
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "助农产品", description = "助农产品相关接口")
public class AgriculturalProductController {

    private final AgriculturalProductService agriculturalProductService;

    @GetMapping
    @Operation(summary = "获取助农产品列表")
    public Result<IPage<AgriculturalProduct>> getAgriculturalProductList(
            @Parameter(description = "页码", example = "1") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "每页数量", example = "10") @RequestParam(defaultValue = "10") int pageSize) {
        IPage<AgriculturalProduct> productPage = agriculturalProductService.getAgriculturalProductPage(page, pageSize);
        return Result.success(productPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取助农产品详情")
    public Result<AgriculturalProduct> getAgriculturalProductDetail(@Parameter(description = "产品ID") @PathVariable Long id) {
        AgriculturalProduct product = agriculturalProductService.getAgriculturalProductById(id);
        if (product == null) {
            return Result.error("产品不存在或已下架");
        }
        return Result.success(product);
    }
}
