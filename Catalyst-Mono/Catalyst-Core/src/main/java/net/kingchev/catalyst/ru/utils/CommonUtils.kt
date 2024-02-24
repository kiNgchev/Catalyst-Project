package net.kingchev.catalyst.ru.utils

import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.time.DurationFormatUtils
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.util.UriUtils
import java.awt.Color
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.DecimalFormat
import java.text.NumberFormat
import java.time.Duration
import java.time.OffsetDateTime
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.stream.Stream

object CommonUtils {
    const val EVERYONE: String = "@everyone"

    const val ZERO_WIDTH_SPACE: String = "\u200E"

    val HTTP_TIMEOUT: Int = TimeUnit.SECONDS.toMillis(10).toInt()

    val HTTP_TIMEOUT_DURATION: Duration = Duration.ofSeconds(10)

    const val EMPTY_SYMBOL: String = "\u2800"

    private val HOURS_FORMAT: DateTimeFormatter = DateTimeFormat.forPattern("HH:mm:ss").withZone(DateTimeZone.UTC)

    private val MINUTES_FORMAT: DateTimeFormatter = DateTimeFormat.forPattern("mm:ss").withZone(DateTimeZone.UTC)

    private val SECONDS_FORMAT: DateTimeFormatter = DateTimeFormat.forPattern("ss").withZone(DateTimeZone.UTC)

    private val VK_LINK_TAG: Pattern = Pattern.compile("\\[([0-9a-zA-Z_\\.]+)\\|([^\\|\\[\\]]+)\\]")

    private val VK_HASH_TAG: Pattern = Pattern.compile("(#[0-9a-zA-Zа-яА-Я_#@]+)")

    private val CODE_PATTERN: Pattern = Pattern.compile("\\s*```(groovy\\s+)?((.|\\n)+)```\\s*", Pattern.MULTILINE)

    private fun CommonUtils() {
        // helper class
    }

    @JvmStatic
    fun createRequestFactory(): HttpComponentsClientHttpRequestFactory {
        val httpRequestFactory = HttpComponentsClientHttpRequestFactory()
        httpRequestFactory.setConnectTimeout(HTTP_TIMEOUT)
        httpRequestFactory.setConnectionRequestTimeout(HTTP_TIMEOUT)
        return httpRequestFactory
    }

    @JvmStatic
    fun formatDuration(millis: Long): String {
        var millis = millis
        if (millis < 0) {
            millis = 0
        }
        var format = "mm:ss"
        if (millis > 3600000) {
            format = "HH:mm:ss"
        }
        return DurationFormatUtils.formatDuration(millis, format)
    }

    @JvmStatic
    fun <T> coalesce(vararg objects: T): T? {
        return Stream.of(*objects).filter { obj: T? ->
            Objects.nonNull(
                obj
            )
        }.findFirst().orElse(null)
    }

    @JvmStatic
    fun trimTo(content: String, length: Int): String {
        var content = content
        if (StringUtils.isEmpty(content)) {
            return content
        }
        if (content.length > length) {
            content = content.substring(0, length - 3) + "..."
        }
        return content
    }

    @JvmStatic
    fun formatNumber(number: Long): String {
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        val symbols = formatter.decimalFormatSymbols

        symbols.groupingSeparator = ' '
        formatter.decimalFormatSymbols = symbols
        return formatter.format(number)
    }

    @JvmStatic
    fun trimTo(content: String, minLength: Int, maxLength: Int): String {
        var content = content
        if (StringUtils.isEmpty(content)) {
            return content
        }
        if (content.length > maxLength) {
            content = content.substring(0, maxLength - 3) + "..."
        }
        if (content.length < minLength) {
            val result = StringBuilder(content)
            while (result.length < minLength) {
                result.append("_")
            }
            content = result.toString()
        }

        return content
    }

    @JvmStatic
    fun mdLink(title: String?, url: String?): String {
        return String.format("[%s](%s)", if (StringUtils.isEmpty(title)) url else title, url)
    }

    @JvmStatic
    fun getVolumeIcon(volume: Int): String {
        if (volume > 66) {
            return ":loud_sound:"
        } else if (volume > 33) {
            return ":sound:"
        } else if (volume > 0) {
            return ":speaker:"
        }
        return ":mute:"
    }

    @JvmStatic
    fun parseMillis(string: String): Long? {
        if (StringUtils.isNotEmpty(string)) {
            if (string.matches("^[0-2]?[0-3]:[0-5][0-9]:[0-5][0-9]$".toRegex())) {
                return HOURS_FORMAT.parseMillis(string)
            } else if (string.matches("^[0-5]?[0-9]:[0-5][0-9]$".toRegex())) {
                return MINUTES_FORMAT.parseMillis(string)
            } else if (string.matches("^[0-5]?[0-9]$".toRegex())) {
                return SECONDS_FORMAT.parseMillis(string)
            }
        }
        return null
    }

    @JvmStatic
    fun parseVkLinks(string: String): String {
        return parseVkLinks(string, false)
    }

    @JvmStatic
    fun parseVkLinks(string: String, noLink: Boolean): String {
        var string = string
        if (StringUtils.isEmpty(string)) return string
        var m = VK_LINK_TAG.matcher(string)
        var sb = StringBuffer(string.length)
        while (m.find()) {
            m.appendReplacement(
                sb, if (noLink) m.group(2)
                else String.format("[%s](https://vk.com/%s)", m.group(2), m.group(1))
            )
        }
        m.appendTail(sb)

        string = sb.toString()

        if (!noLink) {
            m = VK_HASH_TAG.matcher(string)
            sb = StringBuffer(string.length)
            while (m.find()) {
                m.appendReplacement(
                    sb, String.format(
                        "[%s](https://vk.com/feed?section=search&q=%s)", m.group(1),
                        UriUtils.encode(m.group(1), "UTF-8")
                    )
                )
            }
            m.appendTail(sb)
            string = sb.toString()
        }
        return string
    }

    @JvmStatic
    fun makeLink(title: String?, url: String?): String {
        return String.format("[%s](%s)", title, url)
    }

    @JvmStatic
    fun unwrapCode(value: String): String {
        if (StringUtils.isEmpty(value)) {
            return value
        }
        val m = CODE_PATTERN.matcher(value)
        if (m.find()) {
            return m.group(2)
        }
        return value
    }

    @JvmStatic
    fun getDate(offsetDateTime: OffsetDateTime): DateTime {
        return DateTime(offsetDateTime.toEpochSecond() * 1000).withZone(DateTimeZone.UTC)
    }

    @JvmStatic
    fun <T> reverse(collection: List<T?>?, part: Collection<T?>?): List<T?> {
        val arrayList: MutableList<T?> = ArrayList(collection)
        if (CollectionUtils.isNotEmpty(part)) {
            arrayList.removeAll(part!!)
        }
        return arrayList
    }

    /**
     * @param colorStr e.g. "FFFFFF"
     * @return
     */
    @JvmStatic
    fun hex2Rgb(colorStr: String): Color {
        var colorStr = colorStr
        if (colorStr.startsWith("#")) {
            colorStr = colorStr.substring(1)
        }
        return Color(
            colorStr.substring(0, 2).toInt(16),
            colorStr.substring(2, 4).toInt(16),
            colorStr.substring(4, 6).toInt(16)
        )
    }

    @JvmStatic
    fun <T : Enum<T>?> safeEnumSet(collection: Collection<*>, type: Class<T>): Set<T> {
        return Stream.of(*type.enumConstants)
            .filter { e: T -> collection.contains(e!!.name) }
            .collect(Collectors.toSet())
    }

    @JvmStatic
    fun <T : Enum<T>?> safeEnumSet(input: String, type: Class<T>): Set<T> {
        return if (StringUtils.isNotEmpty(input)) safeEnumSet(
            Arrays.asList(
                *input.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()), type) else HashSet()
    }

    @JvmStatic
    fun <T : Enum<T>?> enumsString(enums: Set<T?>): String? {
        return if (CollectionUtils.isNotEmpty(enums)) enums.stream().map { obj: T? -> obj!!.name }
            .collect(Collectors.joining(",")) else null
    }

    @JvmStatic
    fun getUTCOffset(zone: DateTimeZone): String {
        val offset: Int = zone.getOffset(DateTime.now())

        val hours = TimeUnit.MILLISECONDS.toHours(offset.toLong())
        val minutes = TimeUnit.MILLISECONDS.toMinutes(offset - TimeUnit.HOURS.toMillis(hours))

        return String.format("UTC%s%d:%02d", if (hours > 0) '+' else '-', hours, minutes)
    }

    @JvmStatic
    fun urlEncode(value: String?): String {
        return URLEncoder.encode(value, StandardCharsets.UTF_8)
    }
}