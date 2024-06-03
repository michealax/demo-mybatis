package com.shane.mybatis.repository;

import com.shane.mybatis.entity.Singer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerRepository{

    List<Singer> selectAllSingers(@Param("start") long start, @Param("limit") long limit);

    List<Singer> selectSingerList();

    Singer getSingerById(@Param("id") int id);
}
