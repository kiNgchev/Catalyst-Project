package net.kingchev.catalyst.ru.api.controllers

import net.kingchev.catalyst.ru.api.dao.UserDao
import net.kingchev.catalyst.ru.api.dto.UserConfigDto
import net.kingchev.catalyst.ru.core.persistence.entity.UserConfig
import net.kingchev.catalyst.ru.core.persistence.repository.UserConfigRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/config/")
class UserConfigController {
    @Autowired
    private lateinit var userDao: UserDao

    @Autowired
    private lateinit var repository: UserConfigRepository

    @GetMapping("/users/{id}")
    fun getUserConfig(@PathVariable("id") id: Long): Mono<ResponseEntity<UserConfigDto>> {
        val config = userDao.getUserConfig(id)
        return config.map { ResponseEntity.ok(it) }
    }

    @PostMapping("/users")
    fun updateUserConfig(@RequestBody config: UserConfigDto): Mono<ResponseEntity<UserConfig>> {
        return userDao.saveUserConfig(config, config.userId ?: -1)
            .map { ResponseEntity.ok(it) }
    }
}