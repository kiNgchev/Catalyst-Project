package net.kingchev.catalyst.ru.command.model;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.kingchev.catalyst.ru.model.exception.DiscordException;

public interface Command {
    boolean doCommand(MessageReceivedEvent event, String content) throws DiscordException;

    boolean isAvailable(User user, Guild guild, Member member);

    CatalystCommand getAnnotation();

    default String getKey() {
        CatalystCommand annotation = getAnnotation();
        return annotation != null ? annotation.key() : null;
    }

    default boolean isHidden() {
        CatalystCommand annotation = getAnnotation();
        return annotation != null && annotation.hidden();
    }

    default boolean isGuildOnly() {
        CatalystCommand annotation = getAnnotation();
        return annotation != null && annotation.guildOnly();
    }

    default boolean isDmOnly() {
        CatalystCommand annotation = getAnnotation();
        return annotation != null && annotation.dmOnly();
    }

    default boolean isDeveloperOnly() {
        CatalystCommand annotation = getAnnotation();
        return annotation != null && annotation.developerOnly();
    }

    default Permission[] getBotPermissions() {
        CatalystCommand annotation = getAnnotation();
        return annotation != null ? annotation.botPermissions() : null;
    }

    default Permission[] getUserPermissions() {
        CatalystCommand annotation = getAnnotation();
        return annotation != null ? annotation.userPermissions() : null;
    }
}
