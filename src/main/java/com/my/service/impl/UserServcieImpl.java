package com.my.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.my.persistence.UserDao;
import com.my.result.UserResult;
import com.my.service.UserService;

@Service("userService")
public class UserServcieImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Cacheable(value = "user", keyGenerator = "myKeyGenerator")
    public UserResult selectByPrimaryKey(Object key) {
        System.out.println("not hit cache");
        return userDao.getUser((Long) key);
    }


    @Override
    @Cacheable(value = "userList", keyGenerator = "myKeyGenerator")
    public List<UserResult> getUserListByTimeRange(Date startDate, Date endDate, long from, long offset) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("from", from);
        map.put("offset", offset);
        return userDao.getUserListByTimeRange(map);
    }



}
