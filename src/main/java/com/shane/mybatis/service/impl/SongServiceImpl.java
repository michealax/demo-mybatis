package com.shane.mybatis.service.impl;

import com.shane.mybatis.constants.RedisKeyConst;
import com.shane.mybatis.entity.Song;
import com.shane.mybatis.repository.SongRepository;
import com.shane.mybatis.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service

public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository songRepository;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Song> selectSong(Integer singerId, String name) {
        return songRepository.selectSong(singerId, name);
    }

    @Override
    public int updateSong(Song song) {
        return songRepository.updateSong(song);
    }

    @Override
    public List<Song> selectSongByOneLabel(Integer singerId, String name) {
        return songRepository.selectByOneLabel(singerId, name);
    }

    @Override
    public List<Song> selectBySingerIds(List<Integer> ids) {
        return songRepository.selectBySingerIds(ids);
    }

    @Override
    public Song selectSongById(Integer id) {

            String key = RedisKeyConst.SongKey.SONG_KEY;
            Song song = (Song) redisTemplate.opsForValue().get(String.format(key, id));
            if (song != null) {
                System.out.println("song from cache - redis");
                return song;
            }

            song = songRepository.selectSongById(id);
            if (song == null) {
                song = new Song();
            }

            redisTemplate.opsForValue().set(String.format(key, song.getId()), song, 30, TimeUnit.SECONDS);
            return song;
    }
}
