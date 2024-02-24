package net.kingchev.catalyst.ru.shared.service

import com.neovisionaries.ws.client.WebSocketFrame
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import lombok.Getter
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.entities.channel.concrete.*
import net.dv8tion.jda.api.events.ExceptionEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.events.session.SessionDisconnectEvent
import net.dv8tion.jda.api.hooks.IEventManager
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.RestAction
import net.dv8tion.jda.api.requests.RestConfig
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.api.sharding.ShardManager
import net.kingchev.catalyst.ru.config.CommonProperties
import net.kingchev.catalyst.ru.config.WorkerProperties
import net.kingchev.catalyst.ru.shared.support.DiscordHttpRequestFactory
import net.kingchev.catalyst.ru.shared.support.JmxJDABean
import net.kingchev.catalyst.ru.support.ModuleListener
import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.lang3.StringUtils
import org.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.jmx.export.MBeanExporter
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*
import javax.security.auth.login.LoginException
import kotlin.concurrent.Volatile

@Service
class DiscordServiceImpl : ListenerAdapter(), DiscordService {
    @Autowired
    private lateinit var workerProperties: WorkerProperties

    @Autowired
    private lateinit var eventManager: IEventManager

    @Getter
    private var shardManager: ShardManager? = null

    @Autowired(required = false)
    private lateinit var moduleListeners: List<ModuleListener>

    @Autowired
    private lateinit var mBeanExporter: MBeanExporter

    @Autowired
    private lateinit var commonProperties: CommonProperties

    @Volatile
    private var cachedUserId: String? = null

    @PostConstruct
    fun init() {
        val token: String = workerProperties.discord.getToken()
        Objects.requireNonNull(token, "No Discord Token specified")
        try {
            RestAction.setPassContext(false)
            val builder = DefaultShardManagerBuilder.createLight(token)
                .setToken(token)
                .setEventManagerProvider { id: Int -> eventManager }
                .addEventListeners(this)
                .setShardsTotal(workerProperties.discord.shardsTotal)
                .setEnableShutdownHook(false)
            shardManager = builder.build()
        } catch (e: LoginException) {
            log.error("Could not login user with specified token", e)
        }
    }

    @PreDestroy
    fun destroy() {
        if (CollectionUtils.isNotEmpty(moduleListeners)) {
            moduleListeners.forEach { listener: ModuleListener ->
                try {
                    listener.onShutdown()
                } catch (e: java.lang.Exception) {
                    log.error("Could not shutdown listener [{}] correctly", listener, e)
                }
            }
        }
        shardManager!!.shutdown()
    }

    override fun onReady(event: ReadyEvent) {
        mBeanExporter.registerManagedResource(JmxJDABean(event.jda))
        setUpStatus()
    }

    override fun onException(event: ExceptionEvent) {
        log.error("JDA error", event.cause)
    }

    override fun onSessionDisconnect(event: SessionDisconnectEvent) {
        val frame: WebSocketFrame? = event.serviceCloseFrame
        if (frame != null) {
            log.warn(
                "WebSocket connection closed with code {}: {}",
                frame.closeCode,
                frame.closeReason
            )
        }
    }

    override fun isConnected(): Boolean {
        return getJda() != null && JDA.Status.CONNECTED == getJda()!!.status
    }

    override fun isConnected(guildId: Long): Boolean {
        return shardManager != null && JDA.Status.CONNECTED == getShard(guildId)!!.status
    }

    override fun getJda(): JDA? {
        if (shardManager == null) {
            return null
        }
        return shardManager!!.shards.iterator().next()
    }

    override fun getSelfUser(): User {
        return getJda()!!.selfUser
    }

    override fun getShardById(guildId: Int): JDA? {
        return shardManager!!.getShardById(guildId)
    }

    override fun getGuildById(guildId: Long): Guild? {
        return shardManager!!.getGuildById(guildId)
    }

    override fun getUserById(userId: Long): User? {
        return shardManager!!.getUserById(userId)
    }

    override fun getUserById(userId: String): User? {
        return shardManager!!.getUserById(userId)
    }

    override fun getTextChannelById(channelId: Long): TextChannel? {
        return shardManager!!.getTextChannelById(channelId)
    }

    override fun getTextChannelById(channelId: String): TextChannel? {
        return shardManager!!.getTextChannelById(channelId)
    }

    override fun getVoiceChannelById(channelId: Long): VoiceChannel? {
        return shardManager!!.getVoiceChannelById(channelId)
    }

    override fun getVoiceChannelById(channelId: String): VoiceChannel? {
        return shardManager!!.getVoiceChannelById(channelId)
    }

    override fun getCategoryById(channelId: Long): Category? {
        return shardManager!!.getCategoryById(channelId)
    }

    override fun getCategoryById(channelId: String): Category? {
        return shardManager!!.getCategoryById(channelId)
    }

    override fun getPrivateChannelById(channelId: Long): PrivateChannel? {
        return shardManager!!.getPrivateChannelById(channelId)
    }

    override fun getPrivateChannelById(channelId: String): PrivateChannel? {
        return shardManager!!.getPrivateChannelById(channelId)
    }

    override fun getThreadChannelById(channelId: Long): ThreadChannel? {
        return shardManager!!.getThreadChannelById(channelId)
    }

    override fun getThreadChannelById(channelId: String): ThreadChannel? {
        return shardManager!!.getThreadChannelById(channelId)
    }

    override fun getForumChannelById(channelId: Long): ForumChannel? {
        return shardManager!!.getForumChannelById(channelId)
    }

    override fun getForumChannelById(channelId: String): ForumChannel? {
        return shardManager!!.getForumChannelById(channelId)
    }

    override fun getStageChannelById(channelId: Long): StageChannel? {
        return shardManager!!.getStageChannelById(channelId)
    }

    override fun getStageChannelById(channelId: String): StageChannel? {
        return shardManager!!.getStageChannelById(channelId)
    }

    override fun getShard(guildId: Long): JDA? {
        return shardManager!!.getShardById(((guildId shr 22) % workerProperties.getDiscord().getShardsTotal()) as Int)
    }

    override fun getShardManager(): ShardManager? {
        return shardManager
    }

    override fun isSuperUser(user: User?): Boolean {
        return user != null && !commonProperties.discord.superUserId.contains(user.idLong)
    }

    override fun getMember(guildId: Long, userId: Long): Member? {
        val guild = shardManager!!.getGuildById(guildId) ?: return null
        return guild.getMemberById(userId)
    }

    override fun getUserId(): String {
        if (cachedUserId != null) {
            return cachedUserId as String
        }

        var attempt = 0
        val restTemplate: RestTemplate =
            RestTemplate(DiscordHttpRequestFactory(workerProperties.discord.getToken()))
        while (cachedUserId == null && attempt++ < 5) {
            try {
                val response: ResponseEntity<String> = restTemplate.getForEntity<String>(
                    RestConfig.DEFAULT_BASE_URL + "/users/@me",
                    String::class.java
                )
                if (HttpStatus.OK != response.statusCode) {
                    log.warn("Could not get userId, endpoint returned {}", response.statusCode)
                    continue
                }
                val `object` = JSONObject(response.body)
                cachedUserId = `object`.getString("id")
                if (StringUtils.isNotEmpty(cachedUserId)) {
                    break
                }
            } catch (e: Exception) {
                // fall down
            }
            log.error("Could not request my own userId from Discord, will retry a few times")
            try {
                Thread.sleep(5000)
            } catch (ignored: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }

        if (cachedUserId == null) {
            throw RuntimeException("Failed to retrieve my own userId from Discord")
        }
        return cachedUserId!!
    }

    private fun setUpStatus() {
        shardManager!!.setStatus(OnlineStatus.IDLE)
        val playingStatus: String = workerProperties.discord.playingStatus
        if (StringUtils.isNotEmpty(playingStatus)) {
            shardManager!!.setActivity(Activity.playing(playingStatus))
        }
    }
    
    companion object {
        private var log = LoggerFactory.getLogger(DiscordServiceImpl::class.java)
    }
}