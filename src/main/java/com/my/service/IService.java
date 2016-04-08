package com.my.service;

public interface IService<R> {

    R selectByPrimaryKey(Object key);

}
