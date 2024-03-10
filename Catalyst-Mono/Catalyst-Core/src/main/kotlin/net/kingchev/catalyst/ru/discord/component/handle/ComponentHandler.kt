package net.kingchev.catalyst.ru.discord.component.handle

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.kingchev.catalyst.ru.discord.event.model.CatalystEvent
import net.kingchev.catalyst.ru.discord.event.model.Event

@CatalystEvent(eventName = "ComponentHandlerEvent")
class ComponentHandler : ListenerAdapter(), Event {
    override fun onButtonInteraction(event: ButtonInteractionEvent) {
        super.onButtonInteraction(event)
    }

    override fun onStringSelectInteraction(event: StringSelectInteractionEvent) {
        super.onStringSelectInteraction(event)
    }

    override fun onEntitySelectInteraction(event: EntitySelectInteractionEvent) {
        super.onEntitySelectInteraction(event)
    }

    override fun onModalInteraction(event: ModalInteractionEvent) {
        super.onModalInteraction(event)
    }
}