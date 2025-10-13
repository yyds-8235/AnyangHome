package com.example.anyanghome.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 助农产品实体类
 */
@Data
@TableName("agricultural_product")
public class AgriculturalProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 产品名称
     */
    @TableField("name")
    private String name;

    /**
     * 产品主图URL
     */
    @TableField("image_url")
    private String imageUrl;

    /**
     * 产品图集（JSON格式存储多个图片URL）
     */
    @TableField("images")
    private String images;

    /**
     * 产品简短描述
     */
    @TableField("description")
    private String description;

    /**
     * 产品详细介绍（富文本）
     */
    @TableField("full_description")
    private String fullDescription;

    /**
     * 产地信息
     */
    @TableField("origin")
    private String origin;

    /**
     * 营养价值信息
     */
    @TableField("nutrition_info")
    private String nutritionInfo;

    /**
     * 种植过程描述
     */
    @TableField("planting_process")
    private String plantingProcess;

    /**
     * PC端线上商城链接
     */
    @TableField("ecommerce_link")
    private String ecommerceLink;

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
