package net.kingchev.catalyst.ru.api.controllers

import net.kingchev.catalyst.ru.api.dao.GuildDao
import net.kingchev.catalyst.ru.api.dto.GuildConfigDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/config/")
class GuildConfigController {
    @Autowired
    private lateinit var guildDao: GuildDao

    @GetMapping("/guilds/{id}")
    fun getUserConfig(@PathVariable("id") id: String): ResponseEntity<GuildConfigDto> {
        val _id = id.toLong()
        val config = guildDao.getGuildConfig(_id)
        return ResponseEntity.ok(config)
    }

    @PostMapping("/guilds")
    fun updateUserConfig(@RequestBody config: GuildConfigDto): ResponseEntity<GuildConfigDto> {
        guildDao.saveUserConfig(config, config.guildId ?: -1)
        return ResponseEntity.ok(config)
    }
}