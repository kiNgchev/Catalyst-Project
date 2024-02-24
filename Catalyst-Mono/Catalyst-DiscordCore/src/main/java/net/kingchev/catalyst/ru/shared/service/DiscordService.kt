package net.kingchev.catalyst.ru.shared.service

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.entities.channel.concrete.Category
import net.dv8tion.jda.api.entities.channel.concrete.ForumChannel
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel
import net.dv8tion.jda.api.entities.channel.concrete.StageChannel
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel
import net.dv8tion.jda.api.sharding.ShardManager

interface DiscordService {
    fun getUserId(): String

    fun getJda(): JDA?

    fun getSelfUser(): User?

    fun getShardById(guildId: Int): JDA?

    fun getGuildById(guildId: Long): Guild?

    fun getUserById(userId: Long): User?

    fun getUserById(userId: String): User?

    fun getTextChannelById(channelId: Long): TextChannel?

    fun getTextChannelById(channelId: String): TextChannel?

    fun getVoiceChannelById(channelId: Long): VoiceChannel?

    fun getVoiceChannelById(channelId: String): VoiceChannel?

    fun getCategoryById(channelId: Long): Category?

    fun getCategoryById(channelId: String): Category?

    fun getPrivateChannelById(channelId: Long): PrivateChannel?

    fun getPrivateChannelById(channelId: String): PrivateChannel?

    fun getThreadChannelById(channelId: Long): ThreadChannel?

    fun getThreadChannelById(channelId: String): ThreadChannel?

    fun getForumChannelById(channelId: Long): ForumChannel?

    fun getForumChannelById(channelId: String): ForumChannel?

    fun getStageChannelById(channelId: Long): StageChannel?

    fun getStageChannelById(channelId: String): StageChannel?

    fun getShard(guildId: Long): JDA?

    fun getShardManager(): ShardManager?

    fun isConnected(): Boolean

    fun isConnected(guildId: Long): Boolean

    fun isSuperUser(user: User?): Boolean

    fun getMember(guildId: Long, userId: Long): Member?
}