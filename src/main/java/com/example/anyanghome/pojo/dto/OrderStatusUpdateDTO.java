package com.example.anyanghome.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 订单状态更新DTO
 */
@Data
@Schema(description = "订单状态更新DTO")
public class OrderStatusUpdateDTO {

    @NotBlank(message = "订单状态不能为空")
    @Schema(description = "订单状态 (pending: 待支付, paid: 已支付, completed: 已完成, cancelled: 已取消)")
    private String status;

    @Schema(description = "备注信息")
    private String remark;
}