package net.kingchev.catalyst.ru.core.model

import net.dv8tion.jda.api.entities.Guild.BoostTier
import net.dv8tion.jda.api.entities.Guild.VerificationLevel
import net.kingchev.catalyst.ru.core.utils.LocaleUtils

data class GuildInfo(
    val name: String = "undefined",
    val description: String = "undefined",
    val memberCount: Int = -1,
    val botCount: Int = -1,
    val textChannelCount: Int = -1,
    val voiceChannelCount: Int = -1,
    val forumChannelCount: Int = -1,
    val stageChannelCount: Int = -1,
    val threadChannelCount: Int = -1,
    val verificationLevel: VerificationLevel = VerificationLevel.UNKNOWN,
    val boostTier: BoostTier = BoostTier.UNKNOWN,
    val locale: String = LocaleUtils.DEFAULT.language,
)
