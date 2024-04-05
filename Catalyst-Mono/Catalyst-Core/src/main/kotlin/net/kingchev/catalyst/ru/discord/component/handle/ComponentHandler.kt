package net.kingchev.catalyst.ru.discord.component.handle

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.kingchev.catalyst.ru.discord.component.service.ComponentHolderService
import net.kingchev.catalyst.ru.discord.event.model.CatalystEvent
import net.kingchev.catalyst.ru.discord.event.model.Event
import org.springframework.beans.factory.annotation.Autowired

@CatalystEvent(eventName = "ComponentHandlerEvent")
class ComponentHandler : ListenerAdapter(), Event {
    @Autowired
    private lateinit var componentHolderService: ComponentHolderService

    override fun onButtonInteraction(event: ButtonInteractionEvent) {
        val component = componentHolderService.getButton(event.componentId)
        component.execute(event)
    }

    override fun onStringSelectInteraction(event: StringSelectInteractionEvent) {
        val component = componentHolderService.getSelect(event.componentId)
        component.execute(event)
    }

    override fun onEntitySelectInteraction(event: EntitySelectInteractionEvent) {
        val component = componentHolderService.getSelect(event.componentId)
        component.execute(event)
    }

    override fun onModalInteraction(event: ModalInteractionEvent) {
        val component = componentHolderService.getModal(event.modalId)
        component.execute(event)
    }
}