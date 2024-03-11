package net.kingchev.catalyst.ru.discord.message.service

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed

interface MessageService {
    fun getBaseEmbed(title: String): EmbedBuilder

    fun getBaseEmbed(title: String, copyright: Boolean): EmbedBuilder

    fun getErrorEmbed(title: String, description: String, vararg objects: Any?): MessageEmbed

    fun getErrorEmbed(title: String, description: String): MessageEmbed
}