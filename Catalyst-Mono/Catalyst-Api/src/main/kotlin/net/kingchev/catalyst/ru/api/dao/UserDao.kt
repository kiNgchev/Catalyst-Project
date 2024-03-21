package net.kingchev.catalyst.ru.api.dao

import net.kingchev.catalyst.ru.api.dto.UserConfigDto
import net.kingchev.catalyst.ru.core.persistence.entity.UserConfig
import net.kingchev.catalyst.ru.core.service.UserConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class UserDao : AbstractDao()  {
    @Autowired
    private lateinit var userConfigService: UserConfigService

    @Transactional
    fun getUserConfig(userId: Long): Mono<UserConfigDto> {
        val config = userConfigService.getById(userId)
            .map { UserConfigDto(it.userId, it.locale) }
        return config
    }

    @Transactional
    fun saveUserConfig(userConfigDto: UserConfigDto, userId: Long): Mono<UserConfig> {
        return userConfigService.getById(userId)
            .flatMap {
                configMapper.updateUserConfig(userConfigDto, it)
                println(it.userId)
                println(userConfigDto)
                userConfigService.save(it)
            }
    }
}