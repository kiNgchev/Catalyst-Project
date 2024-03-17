package net.kingchev.catalyst.ru.core.service.impl

import net.kingchev.catalyst.ru.core.persistence.entity.UserConfig
import net.kingchev.catalyst.ru.core.persistence.repository.UserConfigRepository
import net.kingchev.catalyst.ru.core.service.UserConfigService
import net.kingchev.catalyst.ru.core.utils.LocaleUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cglib.core.Local
import org.springframework.stereotype.Service

@Service
class UserConfigServiceImpl : UserConfigService {
    @Autowired
    private lateinit var repository: UserConfigRepository

    @CachePut(cacheNames = ["user_config"], key = "#id")
    override fun getById(id: Long): UserConfig? {
        return repository.getByUserId(id)
    }

    @CachePut(cacheNames = ["user_config"], key = "#id")
    override fun getById(id: Long, createNew: Boolean): UserConfig {
        if (createNew) return repository.getByUserId(id) ?: createNew(id)
        else throw NullPointerException("Guild with id `$id` not found")
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
        config.locale = LocaleUtils.DEFAULT.language
        return repository.save(config)
    }
}