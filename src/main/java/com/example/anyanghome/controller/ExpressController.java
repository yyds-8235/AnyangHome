package com.example.anyanghome.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.anyanghome.common.Result;
import com.example.anyanghome.pojo.dto.ExpressOrderDTO;
import com.example.anyanghome.pojo.entity.Order;
import com.example.anyanghome.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 快递服务控制器
 */
@RestController
@RequestMapping("/api/express")
@RequiredArgsConstructor
@Tag(name = "快递服务", description = "快递服务相关接口")
public class ExpressController {

    private final OrderService orderService;
    
    // 快递公司映射
    private static final Map<Integer, String> EXPRESS_COMPANIES = new HashMap<>();
    
    static {
        EXPRESS_COMPANIES.put(1, "顺丰速运");
        EXPRESS_COMPANIES.put(2, "圆通速递");
        EXPRESS_COMPANIES.put(3, "中通快递");
        EXPRESS_COMPANIES.put(4, "韵达速递");
        EXPRESS_COMPANIES.put(5, "申通快递");
        EXPRESS_COMPANIES.put(6, "邮政EMS");
    }

    @PostMapping("/create-order")
    @Operation(summary = "创建快递订单")
    public Result<Void> createExpressOrder(@Valid @RequestBody ExpressOrderDTO expressOrderDTO) {
        try {
            Long userId = StpUtil.getLoginIdAsLong();

            // 创建统一订单
            Order order = new Order();
            order.setUserId(userId);
            order.setType("express");
            order.setTitle("快递寄件");
            
            // 将快递公司ID映射为名称
            String companyName = EXPRESS_COMPANIES.getOrDefault(expressOrderDTO.getCompanyId(), "未知快递公司");
            order.setDescription(companyName + "，重量：" + expressOrderDTO.getWeight() + "kg");
            order.setPrice(expressOrderDTO.getTotalPrice());
            order.setStatus("pending");

            // 创建订单详情JSON
            String details = String.format(
                    "{\"senderName\":\"%s\",\"senderPhone\":\"%s\",\"senderAddress\":\"%s\",\"receiverName\":\"%s\",\"receiverPhone\":\"%s\",\"receiverAddress\":\"%s\",\"weight\":%s,\"content\":\"%s\",\"companyId\":%d}",
                    expressOrderDTO.getSenderName(),
                    expressOrderDTO.getSenderPhone(),
                    expressOrderDTO.getSenderAddress(),
                    expressOrderDTO.getReceiverName(),
                    expressOrderDTO.getReceiverPhone(),
                    expressOrderDTO.getReceiverAddress(),
                    expressOrderDTO.getWeight(),
                    expressOrderDTO.getContent(),
                    expressOrderDTO.getCompanyId());
            order.setDetails(details);

            orderService.createOrder(order);
            return Result.success();
        } catch (Exception e) {
            return Result.error("创建订单失败：" + e.getMessage());
        }
    }

}
