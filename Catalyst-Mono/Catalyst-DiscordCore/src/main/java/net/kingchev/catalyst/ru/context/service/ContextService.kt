package net.kingchev.catalyst.ru.context.service

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.requests.RestAction
import java.awt.Color
import java.util.*
import java.util.function.Consumer
import java.util.function.Supplier

interface ContextService {
    fun setLocale(locale: Locale)

    fun setColor(color: Color)

    fun getLocale(): Locale

    fun getColor(): Color

    fun getDefaultColor(): Color

    fun getLocale(localeName: String): Locale

    fun getLocale(guild: Guild): Locale

    fun getLocale(guildId: Long): Locale

    fun initContext(event: GenericEvent)

    fun initContext(guild: Guild)

    fun <T> withContext(guildId: Long, action: Supplier<T>): T

    fun <T> withContext(guildId: Guild, action: Supplier<T>): T

    fun withContext(guildId: Long, action: Runnable)

    fun withContext(guild: Guild, action: Runnable)

    fun withContextAsync(guild: Guild, action: Runnable)

    fun initContext(user: User)

    fun initContext(guildId: Long)

    fun resetContext()

    fun <T> queue(guild: Guild, action: RestAction<T>, success: Consumer<T>)

    fun <T> queue(guildId: Long, action: RestAction<T>, success: Consumer<T>)
}