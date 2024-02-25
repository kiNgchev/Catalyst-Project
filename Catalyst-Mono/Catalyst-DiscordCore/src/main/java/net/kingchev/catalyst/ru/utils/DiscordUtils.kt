package net.kingchev.catalyst.ru.utils

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.entities.channel.ChannelType
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel
import net.dv8tion.jda.api.entities.channel.unions.DefaultGuildChannelUnion
import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.lang3.StringUtils
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.function.Function
import java.util.regex.Pattern
import java.util.stream.Collectors

object DiscordUtils {
    private const val DEFAULT_AVATAR_URL = "https://cdn.discordapp.com/embed/avatars/%s.png"

    private val MEMBER_MENTION_PATTERN: Pattern = Pattern.compile("@(.*?)#([0-9]{4})")

    private val EMOTE_PATTERN: Pattern = Pattern.compile(":([a-zA-Z0-9_]+)(~\\d+)?:")

    private val CHANNEL_WRITE_PERMISSIONS = arrayOf<Permission>(
        Permission.MESSAGE_SEND,
        Permission.MESSAGE_EMBED_LINKS
    )

    @JvmStatic
    fun getDefaultWriteableChannel(guild: Guild): TextChannel? {
        val self = guild.selfMember
        val channel: DefaultGuildChannelUnion? = guild.defaultChannel
        if (channel != null && self.hasPermission(channel, *CHANNEL_WRITE_PERMISSIONS)) {
            return channel.asTextChannel()
        }
        for (textChannel in guild.textChannels) {
            if (self.hasPermission(textChannel, *CHANNEL_WRITE_PERMISSIONS)) {
                return textChannel
            }
        }
        return null
    }

    @JvmStatic
    fun findMember(guild: Guild, name: String?, discriminator: String): Member {
        return guild.getMembersByName(name!!, true)
            .stream()
            .filter { m: Member ->
                m.user.discriminator == discriminator
            }
            .findFirst()
            .orElse(null)
    }

    @JvmStatic
    fun maskPublicMentions(value: String?): String? {
        var value = value ?: return null
        value = value.replace("@everyone", "@\u2063everyone")
        value = value.replace("@here", "@\u2063here")
        return value
    }

    @JvmStatic
    fun formatUser(user: User): String {
        return String.format("%s#%s", user.name, user.discriminator)
    }

    @JvmStatic
    fun getUrl(url: String): String? {
        if (StringUtils.isEmpty(url) || url.length > MessageEmbed.URL_MAX_LENGTH) {
            return null
        }
        if (EmbedBuilder.URL_PATTERN.matcher(url).matches()) {
            return url
        }
        try {
            val result = URLDecoder.decode(url, StandardCharsets.UTF_8)
            if (EmbedBuilder.URL_PATTERN.matcher(result).matches()) {
                return result
            }
        } catch (e: Exception) {
            // nah I don't care
        }
        return null
    }

    @JvmStatic
    fun getHighestRole(member: Member?, vararg permission: Permission?): Role? {
        if (member == null || CollectionUtils.isEmpty(member.roles)) {
            return null
        }
        return member.roles.stream()
            .sorted(Comparator.comparingInt { obj: Role -> obj.position }
                .reversed())
            .filter { e: Role ->
                permission == null || permission.size == 0 || e.hasPermission(
                    *permission
                )
            }
            .findFirst().orElse(null)
    }

    @JvmStatic
    fun getChannel(jda: JDA, type: ChannelType?, channelId: Long): MessageChannel? {
        return when (type) {
            ChannelType.TEXT -> jda.getTextChannelById(channelId)
            ChannelType.PRIVATE -> jda.getPrivateChannelById(channelId)
            else -> null
        }
    }

    @JvmStatic
    fun getMemberKey(member: Member): String {
        return getMemberKey(member.guild, member.user)
    }

    @JvmStatic
    fun getMemberKey(guild: Guild, user: User): String {
        return getMemberKey(guild, user.id)
    }

    @JvmStatic
    fun getMemberKey(guild: Guild, userId: String): String {
        return String.format("%s:%s", guild.id, userId)
    }

    @JvmStatic
    fun getContent(message: Message): String {
        val builder = StringBuilder(
            message
                .contentStripped
                .replace("\u0000".toRegex(), "")
        )
        val attachmentsPart = message.attachments.stream()
            .map { Message.Attachment::getUrl.toString() }
            .filter { obj: String? -> Objects.nonNull(obj) }
            .collect(Collectors.joining(",\n"))
        if (StringUtils.isNotEmpty(attachmentsPart)) {
            if (builder.length > 0) {
                builder.append("\n")
            }
            builder.append("---")
            builder.append(attachmentsPart)
        }
        return builder.toString()
    }

    @JvmStatic
    fun getDefaultAvatarUrl(discriminator: String): String {
        return String.format(DEFAULT_AVATAR_URL, (discriminator.toShort() % 5).toString())
    }
}