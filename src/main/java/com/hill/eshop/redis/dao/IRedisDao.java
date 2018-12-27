package com.hill.eshop.redis.dao;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface IRedisDao {

    int TOKEN_DB = 4;

    Optional<String> get(String key, int dbIndex);

    <T> Optional<T> get(String key, Class<T> clz, int dbIndex);

    <T> Optional<List<T>> getList(String key, Class<T> clz, int dbIndex);

    void set(String key, String value, int dbIndex);

    void set(String key, String value, int dbIndex, int expireSeconds);

    void setAsJSON(String key, Object object, int dbIndex) throws JsonProcessingException;

    void setAsJSON(String key, Object object, int dbIndex, int expireSeconds) throws JsonProcessingException;

    void remove(String key, int dbIndex);

    void sadd(String key, int dbIndex, String... members);

}
