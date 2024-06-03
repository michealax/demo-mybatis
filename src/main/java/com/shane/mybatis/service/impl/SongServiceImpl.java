package com.shane.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.shane.mybatis.entity.Song;
import com.shane.mybatis.repository.SongRepository;
import com.shane.mybatis.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository songRepository;

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
}
