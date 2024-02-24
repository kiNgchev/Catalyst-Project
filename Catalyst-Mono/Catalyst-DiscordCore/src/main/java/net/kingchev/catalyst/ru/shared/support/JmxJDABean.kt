package net.kingchev.catalyst.ru.shared.support

import net.dv8tion.jda.api.JDA
import net.kingchev.catalyst.ru.support.jmx.JmxNamedResource
import org.springframework.jmx.export.annotation.ManagedAttribute
import org.springframework.jmx.export.annotation.ManagedResource
import java.util.concurrent.ScheduledThreadPoolExecutor

@ManagedResource
class JmxJDABean(private var jda: JDA) : JmxNamedResource {

    private var rateLimitPool: ScheduledThreadPoolExecutor = jda.rateLimitPool as ScheduledThreadPoolExecutor


    /* =====================================================
                             COMMON
       ===================================================== */
    @ManagedAttribute(description = "Returns the ping of shard")
    fun getGatewayPing(): Long {
        return jda.gatewayPing
    }

    @ManagedAttribute(description = "Returns the status of shard")
    fun getStatus(): String {
        return jda.status.name
    }

    @ManagedAttribute(description = "Returns the total amount of JSON responses that discord has sent.")
    fun getResponseTotal(): Long {
        return jda.responseTotal
    }

    @ManagedAttribute(description = "Returns the guild count handled by this shard")
    fun getGuildCount(): Long {
        return jda.guildCache.size()
    }

    @ManagedAttribute(description = "Returns the text channels count handled by this shard")
    fun getTextChannelsCount(): Long {
        return jda.textChannelCache.size()
    }

    @ManagedAttribute(description = "Returns the voice channels count handled by this shard")
    fun getVoiceChannelCount(): Long {
        return jda.voiceChannelCache.size()
    }


    /* =====================================================
                         RATE LIMIT POOL
       ===================================================== */
    @ManagedAttribute(description = "[Rate-Limit Pool] Returns the number of threads that execute tasks")
    fun getRatePoolActiveCount(): Int {
        return rateLimitPool.getActiveCount()
    }

    @ManagedAttribute(description = "[Rate-Limit Pool] Return the current pool size")
    fun getRatePoolSize(): Int {
        return rateLimitPool.getPoolSize()
    }

    @ManagedAttribute(description = "[Rate-Limit Pool] Returns the size of the core pool of threads")
    fun getRateCorePoolSize(): Int {
        return rateLimitPool.corePoolSize
    }

    @ManagedAttribute(description = "[Rate-Limit Pool] Sets the core size of the pool")
    fun seRatetCorePoolSize(corePoolSize: Int) {
        rateLimitPool.setCorePoolSize(corePoolSize)
    }

    @ManagedAttribute(description = "[Rate-Limit Pool] Returns the max size allowed in the pool of threads")
    fun getRateMaxPoolSize(): Int {
        return rateLimitPool.maximumPoolSize
    }

    @ManagedAttribute(description = "[Rate-Limit Pool] Sets the max size allowed in the pool of threads")
    fun setRateMaxPoolSize(maxPoolSize: Int) {
        rateLimitPool.setMaximumPoolSize(maxPoolSize)
    }

    @ManagedAttribute(description = "[Rate-Limit Pool] Returns the total number of completed tasks")
    fun getRatePoolCompletedTaskCount(): Long {
        return rateLimitPool.getCompletedTaskCount()
    }

    @ManagedAttribute(description = "[Rate-Limit Pool] Returns the largest number of threads that have been in the pool")
    fun getRateLargestPoolSize(): Int {
        return rateLimitPool.getLargestPoolSize()
    }

    @ManagedAttribute(description = "[Rate-Limit Pool] Returns the size of current queue")
    fun getRatePoolQueueSize(): Int {
        return rateLimitPool.queue.size
    }

    @ManagedAttribute(
        description = "[Rate-Limit Pool] Returns the number of additional elements that this queue can "
                + "accept without  "
                + "blocking"
    )
    fun getRatePoolQueueRemainingCapacity(): Int {
        return rateLimitPool.queue.remainingCapacity()
    }

    @ManagedAttribute(description = "[Rate-Limit Pool] Returns the total number of tasks that have ever been scheduled for execution ")
    fun getRatePoolTaskCount(): Long {
        return rateLimitPool.getTaskCount()
    }

    override fun getJmxName(): String {
        return jda.shardInfo.toString()
    }
}