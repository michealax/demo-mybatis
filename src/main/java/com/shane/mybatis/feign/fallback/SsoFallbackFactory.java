package com.shane.mybatis.feign.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SsoFallbackFactory implements FallbackFactory<SsoFallback> {
    @Override
    public SsoFallback create(Throwable cause) {
        return new SsoFallback();
    }
}
