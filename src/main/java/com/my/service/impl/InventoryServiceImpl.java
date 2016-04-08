package com.my.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.persistence.InventoryDao;
import com.my.service.InventoryService;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryDao inventoryDao;

    @Override
    public Object selectByPrimaryKey(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateInventory(long productId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productId", productId);
        map.put("count", count);
        inventoryDao.reduceInventoryCount(map);
    }

}
