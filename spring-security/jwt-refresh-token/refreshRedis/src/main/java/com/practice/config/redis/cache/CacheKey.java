package com.practice.config.redis.cache;

import lombok.Getter;
import org.springframework.cache.annotation.Cacheable;


//TODO - MemberServiceImpl.loadUserByUsername()에 @Cacheable(value = CacheKey.USER, key = "#username", unless = "#result == null") 에 사용되는 enum
@Getter
public class CacheKey {
    public static final String USER = "user";
    public static final int DEFAULT_EXPIRE_SEC = 60;
}
