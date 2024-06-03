package com.shane.mybatis.constants;

import com.shane.mybatis.config.RedisConfig;

public class RedisKeyConst {

    private RedisKeyConst() {
    }

    public static class SingerKey {
        public static final String SINGER_KEY = "singer:id:{%d}";
    }
}
