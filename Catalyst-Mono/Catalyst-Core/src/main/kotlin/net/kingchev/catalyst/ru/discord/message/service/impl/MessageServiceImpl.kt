package net.kingchev.catalyst.ru.discord.message.service.impl

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import net.kingchev.catalyst.ru.core.config.CoreProperties
import net.kingchev.catalyst.ru.core.localezied.service.LocaleService
import net.kingchev.catalyst.ru.discord.message.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl : MessageService {
    @Autowired
    private lateinit var localeService: LocaleService

    @Autowired
    private lateinit var coreProperties: CoreProperties

    override fun getBaseEmbed(title: String): EmbedBuilder {
        return getBaseEmbed(title, false)
    }

    override fun getBaseEmbed(title: String, copyright: Boolean): EmbedBuilder {
        val builder = EmbedBuilder()
            .setTitle(title)
        if (copyright) builder.setFooter("Футер типа")
        return builder
    }

    override fun getErrorEmbed(title: String, description: String, vararg objects: Any?): MessageEmbed {
        return getErrorEmbed(title, description.format(objects))
    }

    override fun getErrorEmbed(title: String, description: String): MessageEmbed {
        return getBaseEmbed(title)
            .setColor(coreProperties.colors.errorColor)
            .setDescription(description)
            .build()
    }

}