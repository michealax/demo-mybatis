package com.shane.mybatis.controller;

import com.shane.mybatis.dto.ResponseResult;
import com.shane.mybatis.entity.Song;
import com.shane.mybatis.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("")
    public ResponseResult<List<Song>> selectSong(@RequestParam(value = "singerId", required = false) Integer singerId,
                                                 @RequestParam(value = "name", required = false) String name) {
        List<Song> songs = songService.selectSong(singerId, name);
        return ResponseResult.success(songs);
    }

    @PostMapping("/{id}")
    public ResponseResult<Integer> update(@RequestBody Song song, @PathVariable("id") int id) {
        song.setId(id);
        int response = songService.updateSong(song);
        return ResponseResult.success(response);
    }

    @GetMapping("/one")
    public ResponseResult<List<Song>> selectSongByOneLabel(@RequestParam(value = "singerId", required = false) Integer singerId,
                                           @RequestParam(value = "name", required = false) String name) {

        List<Song> songs = songService.selectSongByOneLabel(singerId, name);
        return ResponseResult.success(songs);
    }

    @GetMapping("/ids")
    public ResponseResult<List<Song>> selectSongBySingerIds(@RequestBody List<Integer> ids) {
        List<Song> songs =songService.selectBySingerIds(ids);
        return ResponseResult.success(songs);
    }

    @GetMapping("/{id}")
    public ResponseResult<Song> getSong(@PathVariable Integer id) {
        Song song = songService.selectSongById(id);
        return ResponseResult.success(song);
    }
}
