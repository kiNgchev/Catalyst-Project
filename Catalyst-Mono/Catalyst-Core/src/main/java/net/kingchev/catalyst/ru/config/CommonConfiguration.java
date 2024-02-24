package net.kingchev.catalyst.ru.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@Import({
        RabbitMQConfiguration.class,
        MBeanConfiguration.class
})
@EnableConfigurationProperties
@EnableJpaRepositories
@EnableRedisRepositories
@ComponentScan(basePackages = {"net.kingchev.catalyst.ru"})
public class CommonConfiguration {
    public final static String SCHEDULER = "taskScheduler";

    @Autowired
    private CommonProperties commonProperties;

    @Bean(SCHEDULER)
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(commonProperties.getExecution().getSchedulerPoolSize());
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setAwaitTerminationSeconds(30);
        scheduler.setThreadNamePrefix(SCHEDULER);
        return scheduler;
    }

    @Bean
    @Primary
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(commonProperties.getExecution().getCorePoolSize());
        executor.setMaxPoolSize(commonProperties.getExecution().getMaxPoolSize());
        executor.setThreadNamePrefix("taskExecutor");
        return executor;
    }
}
