package com.example.anyanghome.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.anyanghome.pojo.entity.Order;

/**
 * 统一订单服务接口
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void createOrder(Order order);

    /**
     * 分页查询用户订单
     */
    IPage<Order> getUserOrders(Long userId, int page, int pageSize);

    /**
     * 根据订单ID获取订单详情
     */
    Order getOrderById(Long id);

    /**
     * 更新订单状态（包含业务类型和备注）
     */
    void updateOrderStatus(Long orderId, String status, String remark);
}
