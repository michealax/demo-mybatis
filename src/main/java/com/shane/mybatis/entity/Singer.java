package com.shane.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Singer {
    private Integer id;

    private String name;

    private Integer sex;

    private String pic;

    private String birth;

    private String location;

    private String introduction;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Song> songs;
}
