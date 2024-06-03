package com.shane.mybatis.config;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MybatisConfig {

    @Bean
    public Interceptor paginationInterceptor(){
        Interceptor interceptor = new PaginationInterceptor();
        interceptor.setProperties(new Properties());
        return interceptor;
    }

}
