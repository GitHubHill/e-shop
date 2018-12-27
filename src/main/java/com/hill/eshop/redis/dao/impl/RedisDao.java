package com.hill.eshop.redis.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hill.eshop.redis.dao.IRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class RedisDao implements IRedisDao {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public Optional<String> get(String key, int dbIndex) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(dbIndex);
            String value = jedis.get(key);
            return value == null ? Optional.empty() : Optional.of(value);
        }
    }

    @Override
    public <T> Optional<T> get(String key, Class<T> clz, int dbIndex) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(dbIndex);
            return Optional.of(mapper.readValue(jedis.get(key), clz));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public <T> Optional<List<T>> getList(String key, Class<T> clz, int dbIndex) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(dbIndex);
            return Optional.of(mapper.readValue(jedis.get(key), new TypeReference<List<T>>() {}));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public void set(String key, String value, int dbIndex) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(dbIndex);
            jedis.set(key, value);
        }
    }

    @Override
    public void set(String key, String value, int dbIndex, int expireSeconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(dbIndex);
            jedis.set(key, value);
            jedis.expire(key, expireSeconds);
        }
    }

    @Override
    public void setAsJSON(String key, Object object, int dbIndex) throws JsonProcessingException {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(dbIndex);
            jedis.set(key, mapper.writeValueAsString(object));
        }
    }

    @Override
    public void setAsJSON(String key, Object object, int dbIndex, int expireSeconds) throws JsonProcessingException {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(dbIndex);
            jedis.set(key, mapper.writeValueAsString(object));
            jedis.expireAt(key, expireSeconds);
        }
    }

    @Override
    public void remove(String key, int dbIndex) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(dbIndex);
            jedis.del(key);
        }
    }

    @Override
    public void sadd(String key, int dbIndex, String... members) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(dbIndex);
            jedis.sadd(key, members);
        }
    }

}
