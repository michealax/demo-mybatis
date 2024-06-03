package com.shane.mybatis.controller;

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
    public List<Singer> allSingers(@RequestParam(value = "pageNum", defaultValue = "1") long pageNum,
                                   @RequestParam(value = "limit", defaultValue = "20") long limitNum) {
        return singerService.getAllSingers(pageNum, limitNum);
    }

    @GetMapping("/list")
    public List<Singer> allSingers() {
        return singerService.getAllSingers();
    }

    @GetMapping("/{id}")
    public Singer getSingerAndSongs(@PathVariable int id){
        return singerService.selectById(id);
    }
}
