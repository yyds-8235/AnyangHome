package com.example.anyanghome.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.anyanghome.common.Result;
import com.example.anyanghome.pojo.dto.MessageCreateDTO;
import com.example.anyanghome.pojo.dto.MessagePageDTO;
import com.example.anyanghome.pojo.entity.Message;
import com.example.anyanghome.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 消息控制器
 */
@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
@Tag(name = "消息管理", description = "消息相关接口")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/create")
    @Operation(summary = "创建消息")
    public Result<Message> createMessage(@Valid @RequestBody MessageCreateDTO dto) {
        try {
            Message message = messageService.createMessage(dto);
            return Result.success(message);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询消息列表")
    public Result<IPage<Message>> getMessagePage(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        try {
            MessagePageDTO dto = new MessagePageDTO();
            dto.setCurrent(current);
            dto.setSize(size);
            IPage<Message> page = messageService.getMessagePage(dto);
            return Result.success(page);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/unread-count")
    @Operation(summary = "获取未读消息数量")
    public Result<Integer> getUnreadCount() {
        try {
            Long userId = StpUtil.getLoginIdAsLong();
            Integer count = messageService.getUnreadCount(userId);
            return Result.success(count);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/mark-read/{messageId}")
    @Operation(summary = "标记消息为已读")
    public Result<String> markAsRead(@PathVariable Long messageId) {
        try {
            Long userId = StpUtil.getLoginIdAsLong();
            messageService.markAsRead(messageId, userId);
            return Result.success("标记成功");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/mark-all-read")
    @Operation(summary = "标记所有消息为已读")
    public Result<String> markAllAsRead() {
        try {
            Long userId = StpUtil.getLoginIdAsLong();
            messageService.markAllAsRead(userId);
            return Result.success("标记成功");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
