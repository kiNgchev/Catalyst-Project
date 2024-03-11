package net.kingchev.catalyst.ru.discord.command.model

import net.dv8tion.jda.api.Permission
import org.springframework.stereotype.Component
import java.lang.annotation.Inherited

@Inherited
@Component
@Retention(AnnotationRetention.RUNTIME)
@Target(allowedTargets = [AnnotationTarget.CLASS])
annotation class CatalystCommand(
    val key: String,
    val aliases: Array<String> = [],
    val description: String,
    val permissions: Array<Permission> = [Permission.MESSAGE_SEND, Permission.MESSAGE_EMBED_LINKS],
    val userPermission: Array<Permission> = [],
    val group: String,
    val hidden: Boolean = false,
    val developerOnly: Boolean = false,
    val guildOnly: Boolean = true,
    val dmOnly: Boolean = false
)
