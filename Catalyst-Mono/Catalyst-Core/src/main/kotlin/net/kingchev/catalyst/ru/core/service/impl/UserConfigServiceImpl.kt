package net.kingchev.catalyst.ru.core.service.impl

import net.kingchev.catalyst.ru.core.persistence.entity.UserConfig
import net.kingchev.catalyst.ru.core.persistence.repository.UserConfigRepository
import net.kingchev.catalyst.ru.core.service.UserConfigService
import net.kingchev.catalyst.ru.core.utils.LocaleUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
@CacheConfig(cacheNames = ["user_config_cache"])
class UserConfigServiceImpl : UserConfigService {
    @Autowired
    private lateinit var repository: UserConfigRepository

    @Transactional
    @CachePut(cacheNames = ["user_config"], key = "#id")
    override fun getById(id: Long): Mono<UserConfig> {
        return repository.getByUserId(id).switchIfEmpty(createNew(id))
    }

    @Transactional
    @CachePut(cacheNames = ["user_config"], key = "#id")
    override fun getById(id: Long?): Mono<UserConfig> {
        return getById(id ?: -1)
    }

    @Transactional
    @CacheEvict(cacheNames = ["user_config"])
    override fun save(config: UserConfig): Mono<UserConfig> {
        return repository.save(config)
    }

    @Transactional
    @CacheEvict(cacheNames = ["user_config"])
    override fun delete(config: UserConfig) {
        return repository.delete(config)
            .cast(Unit::class.java)
            .block()!!
    }

    private fun createNew(userId: Long?): Mono<UserConfig> {
        val config = UserConfig()
        config.userId = userId
        config.locale = LocaleUtils.DEFAULT.language
        return repository.save(config)
    }
}