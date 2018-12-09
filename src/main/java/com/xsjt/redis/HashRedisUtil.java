/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:spring-boot-learn  
 * File Name:HashRedisUtil.java  
 * Package Name:com.xsjt.redis
 * 
 * Date:2017年11月14日下午8:17:47
 * ---------------------------------------------------------------------------  
*/

package com.xsjt.redis;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * ClassName:HashRedisUtil Date: 2017年11月14日 下午8:17:47
 * Hash 数据类型
 * @author 
 * @version
 * @param <T>
 * @since JDK 1.8
 */
@Component("hashRedis")
public class HashRedisUtil<T> {

	@Autowired
	protected RedisTemplate<String, Object> redisTemplate;
	@Resource
	protected HashOperations<String, String, Object> hashOperations;
	
	/**
	 * put:(添加).  
	 * @param key
	 * @param hashKey
	 * @param doamin value
	 * @param expire 过期时间(单位:秒),传入 -1 时表示不设置过期时间
	 */
    public void put(String key, String hashKey, T doamin, long expire) {
        hashOperations.put(key, hashKey, doamin);
        if (expire != -1) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * remove:( 删除).  
     * @param key
     * @param hashKey
     */
    public void remove(String key, String hashKey) {
        hashOperations.delete(key, hashKey);
    }

    /**
     * get:(查询).  
     * @param key
     * @param hashKey
     * @return
     */
    public Object get(String key, String hashKey) {
        return hashOperations.get(key, hashKey);
    }

    /**
     * getAll:(获取当前redis库下所有对象).  
     * @param key
     * @return
     */
    public List<Object> getAll(String key) {
        return hashOperations.values(key);
    }

    /**
     * getKeys:(查询查询当前redis库下所有key).  
     * @param key
     * @return
     */
    public Set<String> getKeys(String key) {
        return hashOperations.keys(key);
    }

    /**
     * isKeyExists:(判断key是否存在redis中).  
     * @param key
     * @param hashKey
     * @return
     */
    public boolean isKeyExists(String key, String hashKey) {
        return hashOperations.hasKey(key, hashKey);
    }

    /**
     * count:(查询当前key下缓存数量).  
     * @param key
     * @return
     */
    public long count(String key) {
        return hashOperations.size(key);
    }
}