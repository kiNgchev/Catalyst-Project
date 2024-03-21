package net.kingchev.catalyst.ru.core.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.core.task.TaskExecutor
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.reactive.config.EnableWebFlux

@Import(
    RedisConfiguration::class,
)
@Configuration
@EnableWebFlux
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableR2dbcRepositories(basePackages = ["net.kingchev.catalyst.ru.core.persistence.repository"])
@EntityScan(basePackages = ["net.kingchev.catalyst.ru.core.persistence.entity"])
@ComponentScan(basePackages = ["net.kingchev.catalyst.ru.core"])
class CoreConfiguration {
    @Autowired
    lateinit var coreProperties: CoreProperties;

    @Bean
    fun taskScheduler(): TaskScheduler {
        val scheduler = ThreadPoolTaskScheduler()
        scheduler.poolSize = coreProperties.tasks.schedulerPoolSize
        return scheduler
    }

    @Bean
    fun taskExecutor(): TaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = coreProperties.tasks.corePoolSize
        executor.maxPoolSize = coreProperties.tasks.maxPoolSize
        return executor
    }
}