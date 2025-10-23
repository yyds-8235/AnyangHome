package com.example.anyanghome.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息實體類
 */
@Data
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主鍵ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用戶ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 消息標題
     */
    @TableField("title")
    private String title;

    /**
     * 消息內容
     */
    @TableField("content")
    private String content;

    /**
     * 消息類型 (booking: 預約, payment: 支付, express: 快遞, system: 系統)
     */
    @TableField("type")
    private String type;

    /**
     * 是否已讀 (0: 未讀, 1: 已讀)
     */
    @TableField("is_read")
    private Integer isRead;

    /**
     * 消息創建時間
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 消息更新時間
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
