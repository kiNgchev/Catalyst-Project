package net.kingchev.catalyst.ru.command.model;

import net.dv8tion.jda.api.Permission;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CatalystCommand {
    String key();

    String description();

    Permission[] botPermissions() default { Permission.MESSAGE_SEND, Permission.MESSAGE_EMBED_LINKS };

    Permission[] userPermissions();

    String group() default "discord.group.misc";

    int priority() default 1;

    boolean hidden() default false;

    boolean developerOnly() default false;

    boolean guildOnly() default true;

    boolean dmOnly() default false;
}
