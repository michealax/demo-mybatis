package com.shane.mybatis.controller;

import com.shane.mybatis.dto.ResponseResult;
import com.shane.mybatis.entity.Singer;
import com.shane.mybatis.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @GetMapping("/all")
    public ResponseResult<List<Singer>> allSingers(@RequestParam(value = "pageNum", defaultValue = "1") long pageNum,
                                                   @RequestParam(value = "limit", defaultValue = "20") long limitNum) {
        List<Singer> singers = singerService.getAllSingers(pageNum, limitNum);
        return ResponseResult.success(singers);
    }

    @GetMapping("/list")
    public ResponseResult<List<Singer>> allSingers() {
        List<Singer> singers = singerService.getAllSingers();
        return ResponseResult.success(singers);
    }

    @GetMapping("/{id}")
    public ResponseResult<Singer> getSingerAndSongs(@PathVariable int id) {
        Singer singer = singerService.selectById(id);
        return ResponseResult.success(singer);
    }
}
