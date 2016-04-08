package com.my.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.domain.Order;
import com.my.domain.OrderItem;
import com.my.domain.Product;
import com.my.domain.User;
import com.my.persistence.OrderDao;
import com.my.service.InventoryService;
import com.my.service.OrderService;
import com.my.util.OrderStatus;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    InventoryService inventoryService;

    @Override
    public Object selectByPrimaryKey(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public void newOrder(long userId, Map<Long, Integer> productMaps) {
        int id = (int) (Math.random() * 100000000) + 1;
        Order order = new Order();
        order.setId(id);
        order.setCreateDate(new Date());
        order.setStatus(OrderStatus.NEW);
        User user = new User();
        user.setId(userId);
        order.setUser(user);
        orderDao.saveOrder(order);

        for (Map.Entry<Long, Integer> productEntry : productMaps.entrySet()) {
            int count = productEntry.getValue();
            OrderItem item = new OrderItem();
            Product product = new Product();
            product.setId(productEntry.getKey());
            item.setCount(count);
            item.setId(id);
            item.setOrder(order);
            item.setProduct(product);
            int itemId = (int) (Math.random() * 100000000) + 1;
            item.setId(itemId);
            orderDao.saveOrderItem(item);
            inventoryService.updateInventory(product.getId(), count);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("status", OrderStatus.SUCCEED.getCode());
        orderDao.updateOrderStatus(map);
    }

}
