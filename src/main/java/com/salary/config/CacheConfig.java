package com.salary.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;


/**
 * date: 2021/08/8
 * description: 缓存配置
 * require:
 * author: 庞海
 * version: 2.0
 *
 */

@EnableConfigurationProperties(CacheProperties.class) //开启属性配置绑定功能
@Configuration
@EnableCaching //开启缓存启动类的注解从启动类移到这里
public class CacheConfig {


    /**
     * 配置文件中的东西没有用上；
     * 1、原来文件中的东西没有用上
     * @ConfigurationProperties(prefix = "spring.cache")
     * public class CacheProperties {
     *
     * 2、要让他生效
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory, CacheProperties cacheProperties){
         //缓存配置对象
         RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
         redisCacheConfiguration = redisCacheConfiguration
         //序列化方式：new GenericJackson2JsonRedisSerializer();
        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new FastJsonRedisSerializer<>(Object.class)));


        CacheProperties.Redis redisProperties = cacheProperties.getRedis();

        if (redisProperties.getTimeToLive() != null) {
            redisCacheConfiguration = redisCacheConfiguration.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            redisCacheConfiguration = redisCacheConfiguration.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            redisCacheConfiguration = redisCacheConfiguration.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            redisCacheConfiguration = redisCacheConfiguration.disableKeyPrefix();
        }

        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                 .cacheDefaults(redisCacheConfiguration).build();
    }

}
