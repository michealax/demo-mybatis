package com.shane.mybatis.feign.fallback;

import com.shane.mybatis.feign.SSoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
public class SsoFallback implements SSoClient {
    @Override
    public ResponseEntity<String> hello() {
        log.debug("access payment server hello failed");
        throw new RuntimeException("access payment server hello failed");
    }

    @Override
    public ResponseEntity<String> error() {
        throw new RuntimeException("access payment server error failed");
    }
}
