package net.kingchev.catalyst.ru.discord.shared.service

import net.dv8tion.jda.api.entities.Guild
import net.kingchev.catalyst.ru.core.model.GuildInfo

interface DiscordService {
    fun extractGuildInfo(guild: Guild): GuildInfo
}