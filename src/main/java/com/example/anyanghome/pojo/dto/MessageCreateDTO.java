package com.example.anyanghome.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * 消息創建 DTO
 */
@Data
public class MessageCreateDTO {

    /**
     * 消息標題
     */
    @NotBlank(message = "消息標題不能為空")
    private String title;

    /**
     * 消息內容
     */
    @NotBlank(message = "消息內容不能為空")
    private String content;

    /**
     * 消息類型 (booking: 預約, payment: 支付, express: 快遞, system: 系統)
     */
    @NotBlank(message = "消息類型不能為空")
    private String type;
}
