package com.shane.mybatis.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {
    String value() default "";

    long expireMills() default 30000;

    int retryTimes() default 0;

    long retryDuration() default 200;
}
