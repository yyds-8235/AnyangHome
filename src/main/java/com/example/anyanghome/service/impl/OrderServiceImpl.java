package com.example.anyanghome.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.anyanghome.mapper.OrderMapper;
import com.example.anyanghome.pojo.entity.Order;
import com.example.anyanghome.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 统一订单服务实现类
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(Order order) {
        // 生成订单编号
        order.setOrderNo("ORD" + System.currentTimeMillis());
        orderMapper.insert(order);
    }

    @Override
    public IPage<Order> getUserOrders(Long userId, int page, int pageSize) {
        Page<Order> pageParam = new Page<>(page, pageSize);
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUserId, userId)
                .orderByDesc(Order::getCreateTime);
        return orderMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public void updateOrderStatus(Long orderId, String status, String remark) {
        Order order = orderMapper.selectById(orderId);
        if (order != null) {
            order.setStatus(status);

            orderMapper.updateById(order);
        }
    }
}
