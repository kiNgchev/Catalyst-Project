package net.kingchev.catalyst.ru.api.controllers

import net.kingchev.catalyst.ru.api.dao.UserDao
import net.kingchev.catalyst.ru.api.dto.UserConfigDto
import net.kingchev.catalyst.ru.core.persistence.entity.UserConfig
import net.kingchev.catalyst.ru.core.service.UserConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/config/")
class UserConfigController {
    @Autowired
    private lateinit var userDao: UserDao

    @GetMapping("/users/{id}")
    fun getUserConfig(@PathVariable("id") id: String): ResponseEntity<UserConfigDto> {
        val _id = id.toLong()
        val config = userDao.getUserConfig(_id)
        return ResponseEntity.ok(config)
    }

    @PostMapping("/users")
    fun updateUserConfig(@RequestBody config: UserConfigDto): ResponseEntity<UserConfigDto> {
        userDao.saveUserConfig(config, config.userId ?: -1L)
        return ResponseEntity.ok(config)
    }
}