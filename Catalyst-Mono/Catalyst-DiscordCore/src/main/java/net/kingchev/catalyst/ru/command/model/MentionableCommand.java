package net.kingchev.catalyst.ru.command.model;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.regex.Pattern;

public abstract class MentionableCommand extends AbstractCommand {
    private static final Pattern MESSAGE_PATTERN = Pattern.compile("^(<@!?([0-9]{17,20})>|([0-9]{17,20})|)(.*)");

    @Override
    public final boolean doCommand(MessageReceivedEvent event, String content) {
        MemberReference reference = new MemberReference();

        return doCommand(reference, event, content);
    }

    public void showHelp(MessageReceivedEvent event) {

    }

    public abstract boolean doCommand(MemberReference reference, MessageReceivedEvent event, String Content);

    public abstract MessageEmbed helpEmbed();
}
