package net.kingchev.catalyst.ru.discord.message.service.impl

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import net.kingchev.catalyst.ru.core.localezied.service.LocaleService
import net.kingchev.catalyst.ru.discord.message.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Locale

@Service
class MessageServiceImpl : MessageService {
    @Autowired
    private lateinit var localeService: LocaleService

    override fun getBaseEmbed(title: String): EmbedBuilder {
        return getBaseEmbed(title, false)
    }

    override fun getBaseEmbed(title: String, copyright: Boolean): EmbedBuilder {
        val builder = EmbedBuilder()
            .setTitle(title)
        if (copyright) builder.setFooter("Копирайт типа")
        return builder
    }

    override fun getErrorEmbed(title: String, description: String, vararg objects: Any?): MessageEmbed {
        return getErrorEmbed(title, description.format(objects))
    }

    override fun getErrorEmbed(title: String, description: String): MessageEmbed {
        return getBaseEmbed(title)
            .setDescription(description)
            .build()
    }

}