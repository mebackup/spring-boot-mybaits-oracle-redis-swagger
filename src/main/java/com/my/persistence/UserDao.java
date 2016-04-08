package com.my.persistence;

import java.util.List;
import java.util.Map;

import com.my.result.UserResult;

public interface UserDao {
    public UserResult getUser(long userId);

    public List<UserResult> getUserListByTimeRange(Map<String, Object> map);

}
