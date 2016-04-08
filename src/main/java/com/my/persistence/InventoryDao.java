package com.my.persistence;

import java.util.Map;

public interface InventoryDao {

    void reduceInventoryCount(Map<String, Object> map);

}
