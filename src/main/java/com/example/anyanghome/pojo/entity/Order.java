package com.example.anyanghome.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 统一订单实体类
 */
@Data
@TableName("`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 订单类型 (booking: 约车, express: 快递)
     */
    @TableField("type")
    private String type;

    /**
     * 订单标题
     */
    @TableField("title")
    private String title;

    /**
     * 订单描述
     */
    @TableField("description")
    private String description;

    /**
     * 订单价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 订单状态 (pending: 待支付, paid: 已支付, completed: 已完成, cancelled: 已取消)
     */
    @TableField("status")
    private String status;


    /**
     * 订单详情（JSON格式存储）
     */
    @TableField("details")
    private String details;

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
