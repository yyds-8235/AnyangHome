package com.example.anyanghome.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.anyanghome.pojo.dto.MessageCreateDTO;
import com.example.anyanghome.pojo.dto.MessagePageDTO;
import com.example.anyanghome.pojo.entity.Message;

/**
 * 消息服務接口
 */
public interface MessageService {

    /**
     * 創建消息
     * @param dto 消息創建DTO
     * @return 創建的消息
     */
    Message createMessage(MessageCreateDTO dto);

    /**
     * 分頁查詢用戶消息
     * @param dto 分頁查詢DTO
     * @return 分頁結果
     */
    IPage<Message> getMessagePage(MessagePageDTO dto);

    /**
     * 獲取用戶未讀消息數量
     * @param userId 用戶ID
     * @return 未讀消息數量
     */
    Integer getUnreadCount(Long userId);

    /**
     * 標記消息為已讀
     * @param messageId 消息ID
     * @param userId 用戶ID
     */
    void markAsRead(Long messageId, Long userId);

    /**
     * 標記所有消息為已讀
     * @param userId 用戶ID
     */
    void markAllAsRead(Long userId);
}
