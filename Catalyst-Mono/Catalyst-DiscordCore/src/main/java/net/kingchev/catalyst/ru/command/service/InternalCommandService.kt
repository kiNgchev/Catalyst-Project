package net.kingchev.catalyst.ru.command.service

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

interface InternalCommandService {
    fun isValidKey(event: MessageReceivedEvent, key: String): Boolean

    fun resultEmotion(message: MessageReceivedEvent, emoji: String, messageCode: String?, vararg args: Any)
}