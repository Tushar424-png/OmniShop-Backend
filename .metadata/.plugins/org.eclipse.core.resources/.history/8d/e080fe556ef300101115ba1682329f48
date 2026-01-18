package com.Inventoryservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryRedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public boolean reduceStock(Long productId, int qty) {

        String key = "inventory:" + productId;

        Long remaining = redisTemplate
                .opsForValue()
                .increment(key, -qty); // ATOMIC

        if (remaining < 0) {
            // rollback
            redisTemplate.opsForValue()
                    .increment(key, qty);
            return false;
        }
        return true;
    }

    public Integer getStock(Long productId) {
        Object val = redisTemplate.opsForValue()
                .get("inventory:" + productId);
        return val == null ? 0 : (Integer) val;
    }

    public void setStock(Long productId, int qty) {
        redisTemplate.opsForValue()
                .set("inventory:" + productId, qty);
    }
}
