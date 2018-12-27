package com.hill.eshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hill.eshop.constant.PropName;
import com.hill.eshop.interceptor.TokenInterceptor;
import com.hill.eshop.redis.dao.IRedisDao;
import com.hill.eshop.redis.dao.impl.RedisDao;
import com.hill.eshop.util.Int1DArrayHandler;
import com.hill.eshop.util.PropUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Base64;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    private PropUtils propUtils;

    @Bean
    public Base64.Decoder getBase64Decoder() {
        return Base64.getDecoder();
    }

    public TokenInterceptor getWebMvcConfigurer(){
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getWebMvcConfigurer()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        Int1DArrayHandler.setMapper(objectMapper);
        return objectMapper;
    }

    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(propUtils.getLongProp(PropName.application, "poolMaxWaitMillis"));
        jedisPoolConfig.setMaxTotal(propUtils.getIntProp(PropName.application, "poolMaxTotal"));
        jedisPoolConfig.setMaxIdle(propUtils.getIntProp(PropName.application, "poolMaxIdle"));
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, propUtils.getProp(PropName.application, "host"), propUtils.getIntProp(PropName.application, "port"));

        return jedisPool;
    }

    @Bean
    public IRedisDao getRedisdao(){
        return new RedisDao();
    }
}
