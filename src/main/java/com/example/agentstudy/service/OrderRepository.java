package com.example.agentstudy.service;

import com.example.agentstudy.entity.Order;

public interface OrderRepository {
    void save(Order order);
    // 根据订单号查询；不存在时返回 null
    Order findById(String orderId);


}
