package net.kingchev.catalyst.ru.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
@ConfigurationProperties("core")
@PropertySource("classpath:discord-properties.yaml")
public class WorkerProperties {
    private Discord discord = new Discord();

    private Stats stats = new Stats();

    private Events events = new Events();

    private Aiml aiml = new Aiml();

    private Audit audit = new Audit();

    private Patreon patreon = new Patreon();

    private Commands commands = new Commands();

    @Getter
    @Setter
    @ToString
    public static class Discord {
        @Value("${discord.prod}")
        private boolean production = false;
        private int shardsTotal = 2;
        @Value("${discord.production.token}")
        private String prodToken;
        @Value("${discord.test.token}")
        private String testToken;
        private String playingStatus;
        private long reactionsTtlMs = 3600000;

        public String getToken() {
            if (production) return prodToken;
            return testToken;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Stats {
        private String discordbotsOrgToken;
        private String discordbotsGgToken;
    }

    @Getter
    @Setter
    @ToString
    public static class Events {
        private boolean asyncExecution = true;
        private int corePoolSize = 5;
        private int maxPoolSize = 5;
    }

    @Getter
    @Setter
    @ToString
    public static class Aiml {
        private boolean enabled = true;
        private String brainsPath;
    }

    @Getter
    @Setter
    @ToString
    public static class Audit {
        private int keepMonths = 1;
        private boolean historyEnabled = true;
        private int historyDays = 7;
        private boolean historyEncryption = true;
    }

    @Getter
    @Setter
    @ToString
    public static class Commands {
        private boolean invokeLogging = false;
        private int executionThresholdMs = 1000;
        private List<String> disabled = new ArrayList<>();
    }

    @Getter
    @Setter
    @ToString
    public static class Patreon {
        private String campaignId = "1552419";
        private String webhookSecret;
        private String accessToken;
        private String refreshToken;
        private boolean updateEnabled;
        private int updateInterval = 600000;
    }
}
