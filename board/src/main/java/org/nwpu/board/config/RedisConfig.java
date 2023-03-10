package org.nwpu.board.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置类
 * @author lzy
 */
@Configuration
public class RedisConfig {

    @Bean(name = "redisSerializer")
    public StringRedisSerializer initStringRedisSerializer(){
        return new StringRedisSerializer();
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate initRedisTemplate(RedisConnectionFactory redisConnectionFactory, @Qualifier(value = "redisSerializer")StringRedisSerializer stringSerializer) throws Exception {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置Key的序列化采用StringRedisSerializer
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        //redisTemplate.setStringSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        //初始化参数和初始化工作
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
