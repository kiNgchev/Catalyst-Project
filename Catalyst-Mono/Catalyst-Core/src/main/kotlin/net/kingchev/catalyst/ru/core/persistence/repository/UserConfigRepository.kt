package net.kingchev.catalyst.ru.core.persistence.repository

import net.kingchev.catalyst.ru.core.persistence.entity.UserConfig
import net.kingchev.catalyst.ru.core.persistence.repository.base.UserRepository
import org.springframework.data.redis.core.RedisHash
import org.springframework.stereotype.Repository

@RedisHash
@Repository
interface UserConfigRepository : UserRepository<UserConfig> {
}