package com.shane.mybatis.service;

import com.shane.mybatis.entity.Singer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SingerService {

    List<Singer> getAllSingers(long pageNum, long limitNum);

    List<Singer> getAllSingers();

    Singer selectById(int id);
}
