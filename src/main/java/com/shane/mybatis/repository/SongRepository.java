package com.shane.mybatis.repository;

import com.shane.mybatis.entity.Song;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository {

    List<Song> selectSong(@Param("singerId") Integer singerId,
                          @Param("name") String name);

    int updateSong(Song song);

    List<Song> selectByOneLabel(@Param("singerId") Integer singerId,
                                @Param("name") String name);

    List<Song> selectBySingerIds(@Param("list") List<Integer> singerIds);

    Song selectSongById(Integer id);
}
