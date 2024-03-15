package net.kingchev.catalyst.ru.core.service.impl

import net.kingchev.catalyst.ru.core.persistence.entity.UserConfig
import net.kingchev.catalyst.ru.core.persistence.repository.UserConfigRepository
import net.kingchev.catalyst.ru.core.service.UserConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service

@Service
class UserConfigServiceImpl : UserConfigService {
    @Autowired
    private lateinit var repository: UserConfigRepository

    @CachePut(cacheNames = ["user_config"], key = "#id")
    override fun getById(id: Long?): UserConfig {
        return repository.getByUserId(id) ?: createNew(id)
    }

    @CacheEvict(cacheNames = ["user_config"])
    override fun save(config: UserConfig): UserConfig {
        return repository.save(config)
    }

    @CacheEvict(cacheNames = ["user_config"])
    override fun delete(config: UserConfig) {
        return repository.delete(config)
    }

    private fun createNew(userId: Long?): UserConfig {
        val config = UserConfig()
        config.userId = userId
        return repository.save(config)
    }
}