package com.example.anyanghome.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.anyanghome.mapper.MessageMapper;
import com.example.anyanghome.pojo.dto.MessageCreateDTO;
import com.example.anyanghome.pojo.dto.MessagePageDTO;
import com.example.anyanghome.pojo.entity.Message;
import com.example.anyanghome.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 消息服務實現類
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message createMessage(MessageCreateDTO dto) {
        Message message = new Message();
        message.setUserId(StpUtil.getLoginIdAsLong());
        message.setTitle(dto.getTitle());
        message.setContent(dto.getContent());
        message.setType(dto.getType());
        message.setIsRead(0); // 默認未讀
        
        messageMapper.insert(message);
        return message;
    }

    @Override
    public IPage<Message> getMessagePage(MessagePageDTO dto) {
        Page<Message> page = new Page<>(dto.getCurrent(), dto.getSize());
        return messageMapper.selectMessagePage(page, StpUtil.getLoginIdAsLong());
    }

    @Override
    public Integer getUnreadCount(Long userId) {
        return messageMapper.countUnreadMessages(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void markAsRead(Long messageId, Long userId) {
        LambdaUpdateWrapper<Message> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Message::getId, messageId)
                    .eq(Message::getUserId, userId)
                    .set(Message::getIsRead, 1);
        messageMapper.update(null, updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void markAllAsRead(Long userId) {
        LambdaUpdateWrapper<Message> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Message::getUserId, userId)
                    .eq(Message::getIsRead, 0)
                    .set(Message::getIsRead, 1);
        messageMapper.update(null, updateWrapper);
    }
}
