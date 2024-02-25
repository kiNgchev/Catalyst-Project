package net.kingchev.catalyst.ru.context.service

import lombok.Getter
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.guild.GenericGuildEvent
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent
import net.dv8tion.jda.api.requests.RestAction
import net.kingchev.catalyst.ru.config.CommonProperties
import net.kingchev.catalyst.ru.service.GuildConfigService
import net.kingchev.catalyst.ru.service.TransactionalService
import net.kingchev.catalyst.ru.utils.LocaleUtils
import org.apache.commons.lang3.StringUtils
import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.NamedThreadLocal
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.awt.Color
import java.util.*
import java.util.function.Consumer
import java.util.function.Supplier

@Service
class ContextServiceImpl : ContextService {
    class ContextHolder {
        var locale: Locale? = null
        var color: Color? = null
        var guildId: Long? = null
        var userId: String? = null
    }

    private val MDC_GUILD = "guildId"

    private val MDC_USER = "userId"

    private val localeHolder: ThreadLocal<Locale> = NamedThreadLocal("ContextServiceImpl.Locale")

    private val colorHolder: ThreadLocal<Color> = NamedThreadLocal("ContextServiceImpl.Color")

    private val guildHolder: ThreadLocal<Long> = NamedThreadLocal("ContextServiceImpl.GuildIds")

    @Getter
    private var accentColor: Color? = null

    @Autowired
    private lateinit var commonProperties: CommonProperties

    @Autowired
    private lateinit var configService: GuildConfigService

    @Autowired
    private lateinit var resolverService: SourceResolverService

    @Autowired
    private lateinit var transactionService: TransactionalService

    override fun getLocale(): Locale {
        var locale = localeHolder.get()
        if (locale == null) {
            val guildId = guildHolder.get()
            if (guildId != null) {
                setLocale(configService.getLocale(guildId)!!)
                locale = localeHolder.get()
            }
        }
        return locale ?: LocaleUtils.getDefaultLocale()
    }

    override fun getColor(): Color {
        var color = colorHolder.get()
        if (color == null) {
            val guildId = guildHolder.get()
            if (guildId != null) {
                setColor(Color.decode(configService.getColor(guildId)))
                color = colorHolder.get()
            }
        }
        return color ?: getDefaultColor()
    }

    override fun getDefaultColor(): Color {
        if (accentColor == null) {
            val defaultAccentColor: String = commonProperties.discord.defaultAccentColor
            accentColor =  Color.decode(defaultAccentColor)
        }
        return accentColor!!
    }

    override fun getLocale(localeName: String): Locale {
        return LocaleUtils.getOrDefault(localeName)
    }

    override fun getLocale(guild: Guild): Locale {
        return getLocale(guild.idLong)
    }

    override fun getLocale(guildId: Long): Locale {
        return LocaleUtils.getOrDefault(configService.getLocale(guildId)!!)
    }

    override fun setLocale(locale: Locale) {
        if (locale == null) {
            localeHolder.remove()
        } else {
            localeHolder.set(locale)
        }
    }

    override fun setColor(color: Color) {
        if (color == null) {
            colorHolder.remove()
        } else {
            colorHolder.set(color)
        }
    }

    fun setGuildId(guildId: Long) {
        if (guildId == null) {
            guildHolder.remove()
        } else {
            guildHolder.set(guildId)
        }
    }

    override fun initContext(event: GenericEvent) {
        var guild: Guild? = null
        var user: User? = null

        if (event is GenericGuildMemberEvent) {
            val memberEvent = event
            guild = memberEvent.member.guild
            user = memberEvent.member.user
        }

        if (guild == null && event is GenericGuildEvent) {
            guild = event.guild
        }

        if (guild == null || user == null) {
            val member: Member = resolverService.getMember(event)
            if (member != null) {
                guild = member.guild
                user = member.user
            }
        }
        if (guild == null) {
            guild = resolverService.getGuild(event)
        }
        if (user == null) {
            user = resolverService.getUser(event)
        }
        if (guild != null) {
            initContext(guild)
        }
        if (user != null) {
            initContext(user)
        }
    }

    override fun initContext(guild: Guild) {
        initContext(guild.idLong)
    }

    override fun <T> withContext(guildId: Long, action: Supplier<T>): T {
        val holder = arrayOfNulls<Any>(1)
        withContext(guildId) {
            holder[0] = action.get()
        }
        return holder[0] as T
    }

    override fun <T> withContext(guild: Guild, action: Supplier<T>): T {
        val holder = arrayOfNulls<Any>(1)
        withContext(guild) {
            holder[0] = action.get()
        }
        return holder[0] as T
    }

    override fun withContext(guildId: Long, action: Runnable) {
        withContext(guildId,
            { guildId: Long -> this.initContext(guildId) }, action
        )
    }

    override fun withContext(guild: Guild, action: Runnable) {
        withContext<Guild>(guild,
            { guild: Guild ->
                this.initContext(
                    guild
                )
            }, action
        )
    }

    private fun <T> withContext(value: T?, init: Consumer<T>, action: Runnable) {
        if (value == null) {
            action.run()
            return
        }
        val currentContext = getContext()
        resetContext()
        init.accept(value)
        try {
            action.run()
        } finally {
            setContext(currentContext)
        }
    }

    @Async
    override fun withContextAsync(guild: Guild, action: Runnable) {
        transactionService.runInTransaction { withContext(guild, action) }
    }

    override fun <T> queue(guild: Guild, action: RestAction<T>, success: Consumer<T>) {
        action.queue { e: T ->
            withContext(
                guild
            ) { success.accept(e) }
        }
    }

    override fun <T> queue(guildId: Long, action: RestAction<T>, success: Consumer<T>) {
        action.queue { e: T ->
            withContext(
                guildId
            ) { success.accept(e) }
        }
    }

    override fun initContext(user: User) {
        if (user != null) {
            MDC.put(MDC_USER, user.id)
        }
    }

    override fun initContext(guildId: Long) {
        MDC.put(MDC_GUILD, guildId.toString())
        guildHolder.set(guildId)
    }

    override fun resetContext() {
        MDC.remove(MDC_GUILD)
        MDC.remove(MDC_USER)
        guildHolder.remove()
        localeHolder.remove()
        colorHolder.remove()
    }

    fun getContext(): ContextHolder {
        val holder = ContextHolder()
        holder.guildId = guildHolder.get()
        holder.locale = localeHolder.get()
        holder.color = colorHolder.get()
        holder.userId = MDC.get(MDC_USER)
        return holder
    }

    fun setContext(holder: ContextHolder) {
        setLocale(holder.locale!!)
        setGuildId(holder.guildId!!)
        MDC.put(MDC_GUILD, holder.guildId.toString())
        MDC.put(MDC_USER, holder.userId)
    }

    private fun setLocale(tag: String) {
        setLocale(LocaleUtils.get(tag)!!)
    }
}