package com.example.anyanghome.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 助农政策实体类
 */
@Data
@TableName("agricultural_policy")
public class AgriculturalPolicy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 政策标题
     */
    @TableField("title")
    private String title;

    /**
     * 发布单位
     */
    @TableField("source")
    private String source;

    /**
     * 政策摘要
     */
    @TableField("summary")
    private String summary;

    /**
     * 政策内容（富文本）
     */
    @TableField("content")
    private String content;

    /**
     * 分类ID
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 发布日期
     */
    @TableField("publish_date")
    private LocalDateTime publishDate;

    /**
     * 是否置顶 (0: 否, 1: 是)
     */
    @TableField("is_top")
    private Integer isTop;

    /**
     * 是否启用 (0: 禁用, 1: 启用)
     */
    @TableField("is_enabled")
    private Integer isEnabled;

    /**
     * 阅读量
     */
    @TableField("view_count")
    private Long viewCount;

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
