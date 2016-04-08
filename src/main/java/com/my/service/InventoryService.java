package com.my.service;

public interface InventoryService extends IService {

    void updateInventory(long productId, int count);
}
