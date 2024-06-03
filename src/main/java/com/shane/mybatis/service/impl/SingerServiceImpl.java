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

        long start = (pageNum - 1) * limitNum;
        return singerRepository.selectAllSingers(start, limitNum);
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
