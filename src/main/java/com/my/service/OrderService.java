package com.my.service;

import java.util.Map;

public interface OrderService extends IService{
    void newOrder(long userId, Map<Long, Integer> productMaps);
}
