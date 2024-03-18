package net.kingchev.catalyst.ru.api.dao

import net.kingchev.catalyst.ru.api.dto.UserConfigDto
import net.kingchev.catalyst.ru.core.service.UserConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDao : AbstractDao()  {
    @Autowired
    private lateinit var userConfigService: UserConfigService

    @Transactional
    fun getUserConfig(userId: Long): UserConfigDto {
        val config = userConfigService.getById(userId, true)
        val dto = UserConfigDto(config.userId, config.locale)
        return dto
    }

    @Transactional
    fun saveUserConfig(userConfigDto: UserConfigDto, userId: Long) {
        val config = userConfigService.getById(userId, true)
        configMapper.updateUserConfig(userConfigDto, config)
        userConfigService.save(config)
    }
}