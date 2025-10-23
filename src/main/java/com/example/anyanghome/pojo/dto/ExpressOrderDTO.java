package com.example.anyanghome.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 快递订单DTO
 */
@Data
public class ExpressOrderDTO {

    /**
     * 寄件人姓名
     */
    private String senderName;

    /**
     * 寄件人电话
     */
    private String senderPhone;

    /**
     * 寄件人地址
     */
    private String senderAddress;

    /**
     * 收件人姓名
     */
    private String receiverName;

    /**
     * 收件人电话
     */
    private String receiverPhone;

    /**
     * 收件人地址
     */
    private String receiverAddress;

    /**
     * 重量（kg）
     */
    private BigDecimal weight;

    /**
     * 物品内容
     */
    private String content;

    /**
     * 快递公司ID
     */
    private Integer companyId;

    /**
     * 总价格
     */
    private BigDecimal totalPrice;
}
