package com.example.anyanghome.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 助农资讯轮播图实体类
 */
@Data
@TableName("agricultural_news_banner")
public class AgriculturalNewsBanner implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 轮播图URL
     */
    @TableField("image_url")
    private String imageUrl;

    /**
     * 跳转目标URL
     */
    @TableField("target_url")
    private String targetUrl;

    /**
     * 轮播图标题
     */
    @TableField("title")
    private String title;

    /**
     * 排序权重
     */
    @TableField("sort_order")
    private Integer sortOrder;

    /**
     * 是否启用 (0: 禁用, 1: 启用)
     */
    @TableField("is_enabled")
    private Integer isEnabled;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
