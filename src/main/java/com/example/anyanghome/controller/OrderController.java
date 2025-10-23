package com.example.anyanghome.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.anyanghome.common.Result;
import com.example.anyanghome.pojo.entity.Order;
import com.example.anyanghome.pojo.dto.OrderStatusUpdateDTO;
import com.example.anyanghome.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 订单管理控制器
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "订单管理", description = "订单管理相关接口")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "获取用户订单列表")
    public Result<IPage<Order>> getUserOrders(
            @Parameter(description = "页码", example = "1") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "每页数量", example = "10") @RequestParam(defaultValue = "10") int pageSize) {
        Long userId = StpUtil.getLoginIdAsLong();
        IPage<Order> orders = orderService.getUserOrders(userId, page, pageSize);
        return Result.success(orders);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取订单详情")
    public Result<Order> getOrderDetail(@Parameter(description = "订单ID") @PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return Result.error("订单不存在");
        }
        return Result.success(order);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "更新订单状态")
    public Result<String> updateOrderStatus(
            @Parameter(description = "订单ID") @PathVariable Long id,
            @RequestBody OrderStatusUpdateDTO updateDTO) {
        orderService.updateOrderStatus(id, updateDTO.getStatus(), updateDTO.getRemark());
        if (updateDTO.getStatus().equals("paid")) {
            // 发送支付成功消息
            return Result.success("支付成功");
        } else if (updateDTO.getStatus().equals("cancelled")) {
            // 发送订单取消消息
            return Result.success("订单取消成功");
        }
        return Result.success("订单状态更新成功");
    }
}
