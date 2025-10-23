package com.example.anyanghome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.anyanghome.pojo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 统一订单Mapper接口
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
