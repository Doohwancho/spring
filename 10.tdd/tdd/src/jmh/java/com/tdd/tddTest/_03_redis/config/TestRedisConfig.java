package com.tdd.tddTest._03_redis.config;


import com.tdd.tddTest._03_redis.service.ProductRankingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.cho.ecommerce.domain.product.service")
public class TestRedisConfig {
    
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory("localhost", 6379);

//        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//            .commandTimeout(Duration.ofSeconds(2))
//            .shutdownTimeout(Duration.ZERO)
//            .build();
        
        // Configure connection pooling
//        LettucePoolingClientConfiguration poolConfig = LettucePoolingClientConfiguration.builder()
//            .poolConfig(createPoolConfig())
//            .build();
//
//        factory.setClientConfig(poolConfig);
        factory.afterPropertiesSet();
        return factory;
    }
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
    
    @Bean
    public ProductRankingService productRankingService(
        RedisTemplate<String, Object> redisTemplate) {
        return new ProductRankingService(redisTemplate);
    }
}
