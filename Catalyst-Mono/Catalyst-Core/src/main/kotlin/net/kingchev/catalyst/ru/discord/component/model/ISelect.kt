package net.kingchev.catalyst.ru.discord.component.model

import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu
import net.kingchev.catalyst.ru.discord.component.stereotype.CatalystSelect
import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface ISelect : IComponent {
    fun getAnnotation(): CatalystSelect {
        var annotation: CatalystSelect
        try {
            annotation = javaClass.getAnnotation(CatalystSelect::class.java)
        } catch (_: NullPointerException) {
            log.error("Annotation in command ${javaClass.name} is not defined")
            annotation = CatalystSelect("none")
        }
        return annotation
    }

    fun build(locale: String): SelectMenu

    fun execute(event: StringSelectInteractionEvent): Boolean

    fun execute(event: EntitySelectInteractionEvent): Boolean

    companion object {
        val log: Logger
            get() = LoggerFactory.getLogger("selectLogger")
    }
}