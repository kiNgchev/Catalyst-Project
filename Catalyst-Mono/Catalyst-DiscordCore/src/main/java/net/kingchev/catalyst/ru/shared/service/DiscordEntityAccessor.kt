package net.kingchev.catalyst.ru.shared.service

import net.dv8tion.jda.api.entities.Guild
import net.kingchev.catalyst.ru.persistence.entity.config.GuildConfig

interface DiscordEntityAccessor {
    fun getOrCreate(guild: Guild): GuildConfig
}