package com.shane.mybatis.common.lock;

import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.apache.ibatis.mapping.BoundSql;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceClusterConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RedisDistributedLock {
    private static final String UNLOCK_LUA;

    static {
        UNLOCK_LUA = "if redis.call(\"get\", KEYS[1])==ARGV[1] then\n" +
                "\treturn redis.call(\"del\", KEYS[1])\n" +
                "else\n" +
                "\treturn 0\n" +
                "end";
    }

    private final ThreadLocal<String> lockFlag = new ThreadLocal<>();

    private final StringRedisTemplate redisTemplate;

    public RedisDistributedLock(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean lock(String key, long expire, int retryTimes, long retryDuration) {
        boolean result = setRedis(key, expire);
        while (!result && (retryTimes) > 0) {
            retryTimes--;
            try {
                log.info("lock failed, retrying..." + retryTimes);
                Thread.sleep(retryDuration);
            } catch (InterruptedException e) {
                return false;
            }

            result = setRedis(key, expire);
            retryDuration <<= 1;
            if (retryDuration < 0) {
                return false;
            }
        }

        return result;
    }

    private boolean setRedis(String key, long expire) {
        try {
            RedisCallback<Boolean> redisCallback = connection -> {
                String uuid = UUID.randomUUID().toString();
                lockFlag.set(uuid);
                return connection.set(key.getBytes(), uuid.getBytes(), Expiration.milliseconds(expire), RedisStringCommands.SetOption.ifAbsent());

            };

            return Boolean.TRUE.equals(redisTemplate.execute(redisCallback));
        } catch (Exception e) {
            log.error("redis distributed lock error: " + e.getMessage());
        }

        return false;
    }

    public boolean unlock(String key) {
        boolean success = false;
        try {
            byte[] key1 = key.getBytes();
            byte[] key2 = lockFlag.get().getBytes();

            RedisCallback<Long> redisCallback = connection -> {
                Long res = (Long) connection.eval(UNLOCK_LUA.getBytes(), ReturnType.INTEGER, 1, key1, key2);
                return res;
            };

            Long res = redisTemplate.execute(redisCallback);
            success = res != null && res > 0;
        } catch (Exception e) {
            log.error("unlock failed");
        } finally {
            if (success) {
                lockFlag.remove();
            }
        }
        return success;
    }
}
