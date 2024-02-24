package net.kingchev.catalyst.ru.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Component
@ConfigurationProperties("core")
@PropertySource("classpath:core-properties.yaml")
public class CommonProperties {
    private Jmx jmx = new Jmx();

    private Discord discord = new Discord();

    private Execution execution = new Execution();

    private Branding branding = new Branding();

    @Getter
    @Setter
    @ToString
    public static class Jmx {
        private boolean enabled = true;
        private int port = 9875;
    }

    @Getter
    @Setter
    @ToString
    public static class Discord {
        private String defaultPrefix = "c.";
        private String defaultAccentColor = "#7567ff";
        private List<Long> superUserId = List.of(743878110747033691L, 745291286420127895L);
    }

    @Getter
    @Setter
    @ToString
    public static class Execution {
        private int corePoolSize = 5;
        private int maxPoolSize = 5;
        private int schedulerPoolSize = 10;
    }

    @Getter
    @Setter
    @ToString
    public static class Branding {
        private String avatarUrl            = "";
        private String avatarSmallUrl       = "";
        private String copyrightIconUrl     = "";
        private String websiteUrl           = "";
        private Set<String> websiteAliases  = Set.of("");
    }
}
