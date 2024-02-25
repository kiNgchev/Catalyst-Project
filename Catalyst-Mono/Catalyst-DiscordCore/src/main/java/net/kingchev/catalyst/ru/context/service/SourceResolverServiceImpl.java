package net.kingchev.catalyst.ru.context.service;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Service
public class SourceResolverServiceImpl implements SourceResolverService {

    private Map<Class<? extends GenericEvent>, Method> guildAccessors = new HashMap<>();

    private Map<Class<? extends GenericEvent>, Method> userAccessors = new HashMap<>();

    @Override
    public Guild getGuild(GenericEvent event) {
        if (event == null) {
            return null;
        }
        Class<? extends GenericEvent> clazz = event.getClass();
        Method method;
        if (!guildAccessors.containsKey(clazz)) {
            method = ReflectionUtils.findMethod(clazz, "getGuild");
            guildAccessors.put(clazz, method);
        } else {
            method = guildAccessors.get(clazz);
        }
        if (method != null) {
            try {
                Object result = ReflectionUtils.invokeMethod(method, event);
                if (result instanceof Guild) {
                    return (Guild) result;
                }
            } catch (Exception e) {
                // we don't care
            }
        }
        return null;
    }

    @Override
    public User getUser(GenericEvent event) {
        Object author = getAuthor(event);
        return author instanceof User ? (User) author : null;
    }

    @Override
    public Member getMember(GenericEvent event) {
        Object author = getAuthor(event);
        return author instanceof Member ? (Member) author : null;
    }

    private Object getAuthor(GenericEvent event) {
        if (event == null) {
            return null;
        }
        Class<? extends GenericEvent> clazz = event.getClass();
        Method method;
        if (!userAccessors.containsKey(clazz)) {
            method = ReflectionUtils.findMethod(clazz, "getUser");
            if (method == null) {
                method = ReflectionUtils.findMethod(clazz, "getAuthor");
            }
            userAccessors.put(clazz, method);
        } else {
            method = userAccessors.get(clazz);
        }
        if (method != null) {
            try {
                Object result = ReflectionUtils.invokeMethod(method, event);
                if (result instanceof User) {
                    return result;
                }
            } catch (Exception e) {
                // we don't care
            }
        }
        return null;
    }
}
