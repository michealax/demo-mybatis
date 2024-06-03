package com.shane.mybatis.controller;

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

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("")
    public List<Song> selectSong(@RequestParam(value = "singerId", required = false) Integer singerId,
                                 @RequestParam(value = "name", required = false) String name) {

        return songService.selectSong(singerId, name);
    }

    @PostMapping("/{id}")
    public int update(@RequestBody Song song, @PathVariable("id") int id) {
        song.setId(id);
        return songService.updateSong(song);
    }

    @GetMapping("/one")
    public List<Song> selectSongByOneLabel(@RequestParam(value = "singerId", required = false) Integer singerId,
                                           @RequestParam(value = "name", required = false) String name) {

        return songService.selectSongByOneLabel(singerId, name);
    }

    @GetMapping("/ids")
    public List<Song> selectSongBySingerIds(@RequestBody List<Integer> ids) {

        return songService.selectBySingerIds(ids);
    }

    @GetMapping("/{id}")
    public Song getSong(@PathVariable Integer id){
        return songService.selectSongById(id);
    }
}
