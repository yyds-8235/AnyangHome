package com.example.anyanghome.pojo.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;


/**
 * 消息分頁查詢 DTO
 */
@Data
public class MessagePageDTO {

    /**
     * 當前頁碼
     */
    @Min(value = 1, message = "頁碼必須大於0")
    private Integer current = 1;

    /**
     * 每頁大小
     */
    @Min(value = 1, message = "每頁大小必須大於0")
    private Integer size = 10;

    /**
     * 消息類型 (可選)
     */
    private String type;
}
