package com.shane.mybatis.service.impl;

import com.shane.mybatis.common.annotation.RedisLock;
import com.shane.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    @RedisLock("updateUser")
    public boolean updateUser(Integer id, Integer time) {
        boolean flag = true;
        try {
            log.info("id:{} processing... time: {}", id,time);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("update user failed");
            flag = false;
        }
        return flag;
    }
}
