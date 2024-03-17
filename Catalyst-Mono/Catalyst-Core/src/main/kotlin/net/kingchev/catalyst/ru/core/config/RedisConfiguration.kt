package net.kingchev.catalyst.ru.core.config

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableCaching
class RedisConfiguration {
    @Bean
    fun redisCacheTemplate(redisConnectionFactory: LettuceConnectionFactory?): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.connectionFactory = redisConnectionFactory
        return template
    }

    @Bean
    fun cacheManager(factory: RedisConnectionFactory?): CacheManager {
        val serializer = RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer())

        val redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(serializer)
            .serializeValuesWith(serializer)

        return RedisCacheManager.builder(factory!!)
            .cacheDefaults(redisCacheConfiguration)
            .build()
    }
}