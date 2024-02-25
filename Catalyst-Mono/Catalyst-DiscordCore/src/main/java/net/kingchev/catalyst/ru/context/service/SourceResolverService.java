package net.kingchev.catalyst.ru.context.service;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;

public interface SourceResolverService {

    Guild getGuild(GenericEvent event);

    User getUser(GenericEvent event);

    Member getMember(GenericEvent event);
}
