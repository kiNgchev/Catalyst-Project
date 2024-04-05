package net.kingchev.catalyst.ru.discord.component.model

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.interactions.components.buttons.Button
import net.kingchev.catalyst.ru.discord.component.stereotype.CatalystButton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface IButton : IComponent {
    fun getAnnotation(): CatalystButton {
        var annotation: CatalystButton
        try {
            annotation = javaClass.getAnnotation(CatalystButton::class.java)
        } catch (_: NullPointerException) {
            log.error("Annotation in command ${javaClass.name} is not defined")
            annotation = CatalystButton("none")
        }
        return annotation
    }

    fun build(locale: String): Button

    fun execute(event: ButtonInteractionEvent): Boolean

    companion object {
        val log: Logger
            get() = LoggerFactory.getLogger("buttonLogger")
    }
}