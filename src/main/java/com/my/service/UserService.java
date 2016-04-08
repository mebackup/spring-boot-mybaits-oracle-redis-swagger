package com.my.service;

import java.util.Date;
import java.util.List;

import com.my.result.UserResult;

public interface UserService extends IService<UserResult>{

    List<UserResult> getUserListByTimeRange(Date startDateDate, Date endDateDate, long from, long to);

}
