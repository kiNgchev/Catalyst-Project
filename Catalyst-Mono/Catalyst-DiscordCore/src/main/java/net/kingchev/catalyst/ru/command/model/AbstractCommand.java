package net.kingchev.catalyst.ru.command.model;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.kingchev.catalyst.ru.command.service.InternalCommandService;
import net.kingchev.catalyst.ru.config.CommonProperties;
import net.kingchev.catalyst.ru.shared.service.DiscordEntityAccessor;
import net.kingchev.catalyst.ru.shared.service.DiscordService;
import net.kingchev.catalyst.ru.utils.TimeSequenceParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class AbstractCommand implements Command {
    @Autowired
    protected DiscordService discordService;

    @Autowired
    protected CommonProperties commonProperties;

    @Autowired
    protected InternalCommandService commandsService;

    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    protected DiscordEntityAccessor entityAccessor;

    private CatalystCommand annotation;

    @Override
    public boolean isAvailable(User user, Guild guild, Member member) {
        return true;
    }

    protected boolean ok(MessageReceivedEvent message) {
        commandsService.resultEmotion(message, "✅", null);
        return true;
    }

    protected boolean fail(MessageReceivedEvent message) {
        commandsService.resultEmotion(message, "❌", null);
        return false;
    }

    protected boolean ok(MessageReceivedEvent message, String messageCode, Object... args) {
        commandsService.resultEmotion(message, "✅", messageCode, args);
        return true;
    }

    protected boolean fail(MessageReceivedEvent message, String messageCode, Object... args) {
        commandsService.resultEmotion(message, "❌", messageCode, args);
        return false;
    }

    @Override
    public CatalystCommand getAnnotation() {
        if (annotation == null) {
            annotation = getClass().getDeclaredAnnotation(CatalystCommand.class);
        }
        return annotation;
    }

    protected Pair<String, Long> probeDuration(String input) {
        if (StringUtils.isBlank(input)) {
            return Pair.of(input, null);
        }
        String value = input.trim();
        String[] parts = value.split("\\s+");
        try {
            return Pair.of(value.substring(parts[0].length()).trim(), TimeSequenceParser.parseShort(parts[0]));
        } catch (Exception e) {
            return Pair.of(input, null);
        }
    }
}
