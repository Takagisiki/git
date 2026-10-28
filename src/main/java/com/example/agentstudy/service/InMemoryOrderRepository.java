package com.example.agentstudy.service;

import com.example.agentstudy.entity.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** 学习用内存仓库：程序退出后数据不会保留。 */
public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String, Order> orders = new HashMap<>();

    @Override
    public void save(Order order) {
        //两个参数分别是：
        //- order：要检查的对象。
        //- "订单不能为空"：对象为 null 时的异常提示
        Objects.requireNonNull(order, "订单不能为空");
        // 相同订单号再次保存时，替换原有订单。
        orders.put(order.getOrderId(), order);
    }

    @Override
    public Order findById(String orderId) {
        return orders.get(orderId);
    }
}
