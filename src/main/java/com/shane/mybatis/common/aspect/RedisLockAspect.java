package com.shane.mybatis.common.aspect;

import com.shane.mybatis.common.annotation.RedisLock;
import com.shane.mybatis.common.lock.RedisDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Configuration
@Slf4j
public class RedisLockAspect {

    @Resource
    private RedisDistributedLock redisDistributedLock;

    @Around("@annotation(com.shane.mybatis.common.annotation.RedisLock)")
    public Object around(ProceedingJoinPoint pjp) {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String key = redisLock.value();
        if (StringUtils.isEmpty(key)) {
            Object[] args = pjp.getArgs();
            key = Arrays.toString(args);
        }

        boolean lock = redisDistributedLock.lock(key, redisLock.expireMills(), redisLock.retryTimes(), redisLock.retryDuration());
        if (!lock) {
            log.info("get lock failed, key:{},method: {}", key, method.getName());
            return false;
        }

        log.info("get lock success, key:{},method: {}", key, method.getName());

        try {
            return pjp.proceed();
        } catch (Throwable e) {
            log.error("execute method failed. {}", method.getName());
        } finally {
            boolean unlock = redisDistributedLock.unlock(key);
            log.info("release lock: {}, status: {}", key, unlock);
        }

        return false;
    }
}
