package com.my.persistence;

import java.util.Map;

import com.my.domain.Order;
import com.my.domain.OrderItem;

public interface OrderDao {

    //void newOrder(Map<String, Object> map);

    void saveOrder(Order order);

    void saveOrderItem(OrderItem item);

    void updateOrderStatus(Map<String, Object> map);

}
