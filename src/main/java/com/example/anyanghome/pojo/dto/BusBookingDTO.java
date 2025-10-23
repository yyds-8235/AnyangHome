package com.example.anyanghome.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 客车预约DTO
 */
@Data
public class BusBookingDTO {

    /**
     * 路线ID
     */
    private Long routeId;

    /**
     * 座位号
     */
    private String seatNumber;

    /**
     * 出发地
     */
    private String fromLocation;

    /**
     * 目的地
     */
    private String toLocation;

    /**
     * 发车时间
     */
    private String departureTime;

    /**
     * 到达时间
     */
    private String arrivalTime;

    /**
     * 客车类型
     */
    private String busType;

    /**
     * 客运公司
     */
    private String company;

    /**
     * 票价
     */
    private BigDecimal price;

    /**
     * 联系人
     */
    private String passengerName;

    /**
     * 联系电话
     */
    private String passengerPhone;

    /**
     * 身份证
     */
    private String passengerIdCard;
}
