package com.shane.mybatis.service;

import com.shane.mybatis.entity.Song;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SongService {

    List<Song> selectSong(Integer singerId, String name);

    int updateSong(@Param("song") Song song);

    List<Song> selectSongByOneLabel(Integer singerId, String name);

    List<Song> selectBySingerIds(List<Integer> ids);
}
