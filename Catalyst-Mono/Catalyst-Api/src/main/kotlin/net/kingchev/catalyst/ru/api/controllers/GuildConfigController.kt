package net.kingchev.catalyst.ru.api.controllers

import net.kingchev.catalyst.ru.api.dao.GuildDao
import net.kingchev.catalyst.ru.api.dto.GuildConfigDto
import net.kingchev.catalyst.ru.core.persistence.entity.GuildConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/config/")
class GuildConfigController {
    @Autowired
    private lateinit var guildDao: GuildDao

    @GetMapping("/guilds/{id}")
    fun getGuildConfig(@PathVariable("id") id: Long): Mono<ResponseEntity<GuildConfigDto>> {
        val config = guildDao.getGuildConfig(id)
        return config.map { ResponseEntity.ok(it) }
    }

    @PostMapping("/guilds")
    fun updateGuildConfig(@RequestBody config: GuildConfigDto): Mono<ResponseEntity<GuildConfig>> {
        return guildDao.saveGuildConfig(config, config.guildId ?: -1)
            .map { ResponseEntity.ok(it) }
    }
}