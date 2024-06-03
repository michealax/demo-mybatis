package com.shane.mybatis.service.impl;


import com.github.pagehelper.PageHelper;
import com.shane.mybatis.config.RedisConfig;
import com.shane.mybatis.constants.RedisKeyConst;
import com.shane.mybatis.entity.Singer;
import com.shane.mybatis.repository.SingerRepository;
import com.shane.mybatis.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SingerServiceImpl implements SingerService {
    @Resource
    private RedisTemplate<String, Singer> redisTemplate;

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public List<Singer> getAllSingers(long pageNum, long limitNum) {
        if (pageNum < 0 || limitNum <= 0) {
            return new ArrayList<>();
        }
        String key = "singer:%d:%d";
        List<Singer> singers = redisTemplate.opsForList().range(String.format(key, pageNum, limitNum), 0L,-1L);
        if(singers != null && singers.size() > 0){
            System.out.println("singers from cached");
            return singers;
        }
        long start = (pageNum - 1) * limitNum;
        singers = singerRepository.selectAllSingers(start, limitNum);
        if (singers == null || singers.isEmpty()){
            singers = new ArrayList<>();
            singers.add(new Singer());
        }
        redisTemplate.opsForList().rightPushAll(String.format(key, pageNum, limitNum),singers);
        redisTemplate.expire(String.format(key, pageNum, limitNum),10,TimeUnit.SECONDS);
        return singers;
    }

    @Override
    public List<Singer> getAllSingers() {
        PageHelper.startPage(10000, 10);
        System.out.println(PageHelper.getLocalPage());
        List<Singer> singers = singerRepository.selectSingerList();
        System.out.println(PageHelper.getLocalPage());
        return singers;
    }

    @Override
    public Singer selectById(int id) {

        Singer singer = redisTemplate.opsForValue().get(String.format(RedisKeyConst.SingerKey.SINGER_KEY, id));
        if (singer != null) {
            System.out.println("Sing comes from cache - redis");
            return singer;
        }
        singer = singerRepository.getSingerById(id);
        if (singer == null) {
            singer = new Singer();
        }
        redisTemplate.opsForValue().set(String.format(RedisKeyConst.SingerKey.SINGER_KEY, singer.getId()), singer, 30, TimeUnit.SECONDS);
        return singer;
    }

}
