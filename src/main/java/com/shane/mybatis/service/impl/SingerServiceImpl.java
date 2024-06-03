package com.shane.mybatis.service.impl;


import com.github.pagehelper.PageHelper;
import com.shane.mybatis.entity.Singer;
import com.shane.mybatis.repository.SingerRepository;
import com.shane.mybatis.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerRepository singerRepository;
    @Override
    public List<Singer> getAllSingers(long pageNum, long limitNum) {
        if (pageNum < 0 || limitNum <= 0){
            return new ArrayList<>();
        }

        long start = (pageNum - 1) * limitNum;
        return singerRepository.selectAllSingers(start, limitNum);
    }

    @Override
    public List<Singer> getAllSingers() {
        PageHelper.startPage(10000,10);
        System.out.println(PageHelper.getLocalPage());
        List<Singer> singers = singerRepository.selectSingerList();
        System.out.println(PageHelper.getLocalPage());
        return singers;
    }

    @Override
    public Singer selectById(int id) {
        return singerRepository.getSingerById(id);
    }

}
