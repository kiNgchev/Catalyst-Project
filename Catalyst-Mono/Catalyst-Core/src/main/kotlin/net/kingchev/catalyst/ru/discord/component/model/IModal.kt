package net.kingchev.catalyst.ru.discord.component.model

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.interactions.modals.Modal
import net.kingchev.catalyst.ru.discord.component.stereotype.CatalystModal
import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface IModal : IComponent {
    fun getAnnotation(): CatalystModal {
        var annotation: CatalystModal
        try {
            annotation = javaClass.getAnnotation(CatalystModal::class.java)
        } catch (_: NullPointerException) {
            log.error("Annotation in command ${javaClass.name} is not defined")
            annotation = CatalystModal("none")
        }
        return annotation
    }

    fun build(locale: String): Modal

    fun execute(event: ModalInteractionEvent): Boolean

    companion object {
        val log: Logger
            get() = LoggerFactory.getLogger("modalLogger")
    }
}