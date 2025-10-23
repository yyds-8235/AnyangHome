package com.example.anyanghome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.anyanghome.pojo.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 消息Mapper接口
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 分頁查詢用戶消息
     * @param page 分頁對象
     * @param userId 用戶ID
     * @return 分頁結果
     */
    IPage<Message> selectMessagePage(Page<Message> page, @Param("userId") Long userId);

    /**
     * 獲取用戶未讀消息數量
     * @param userId 用戶ID
     * @return 未讀消息數量
     */
    Integer countUnreadMessages(@Param("userId") Long userId);
}
