package com.example.anyanghome.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 客车路线实体类
 */
@Data
@TableName("bus_route")
public class BusRoute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 出发地
     */
    @TableField("from_location")
    private String fromLocation;

    /**
     * 目的地
     */
    @TableField("to_location")
    private String toLocation;

    /**
     * 发车时间
     */
    @TableField("departure_time")
    private String departureTime;

    /**
     * 到达时间
     */
    @TableField("arrival_time")
    private String arrivalTime;

    /**
     * 票价
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 总座位数
     */
    @TableField("total_seats")
    private Integer totalSeats;

    /**
     * 可用座位数
     */
    @TableField("available_seats")
    private Integer availableSeats;

    /**
     * 客车类型
     */
    @TableField("bus_type")
    private String busType;

    /**
     * 客运公司
     */
    @TableField("company")
    private String company;

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
