package net.kingchev.catalyst.ru.core.utils

import org.apache.commons.lang3.StringUtils
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

object TimeUtils {
    enum class FieldType(val type: Int, val maxUnits: Int, vararg val patterns: Pattern) {
        MONTH(Calendar.MONTH, 11, Pattern.compile("^месяц(а|ев)?$"), Pattern.compile("^months?$")),
        WEEK(Calendar.WEEK_OF_YEAR, 31, Pattern.compile("^недел[юиь]$"), Pattern.compile("^weeks?$")),
        DAY(Calendar.DAY_OF_YEAR, 6, Pattern.compile("^день|дн(я|ей)$"), Pattern.compile("^days?$")),
        HOUR(Calendar.HOUR_OF_DAY, 23, Pattern.compile("^час(а|ов)?$"), Pattern.compile("^hours?$")),
        MINUTE(Calendar.MINUTE, 59, Pattern.compile("^минут[уы]?$"), Pattern.compile("^minutes?$")),
        SECOND(Calendar.SECOND, 59, Pattern.compile("^секунд[уы]?$"), Pattern.compile("^seconds?$")),
        MILLISECOND(
            Calendar.MILLISECOND,
            999,
            Pattern.compile("^миллисекунд[уы]?$"),
            Pattern.compile("^milliseconds?$")
        );

        companion object {
            fun find(value: String?): FieldType? {
                for (type: FieldType in entries) {
                    for (pattern: Pattern in type.patterns) {
                        if (pattern.matcher(value).find()) {
                            return type
                        }
                    }
                }
                return null
            }
        }
    }

    private val FULL_PATTERN: Pattern = Pattern.compile("(\\d+)\\s+([a-zA-Zа-яА-Я]+)")

    private val SHORT_PATTERN: Pattern = Pattern.compile(
        "^" +
                "((\\d+)(y|year|years|г|год|года|лет))?" +
                "((\\d+)(mo|mos|month|months|мес|месяц|месяца|месяцев))?" +
                "((\\d+)(w|week|weeks|н|нед|неделя|недели|недель|неделю]))?" +
                "((\\d+)(d|day|days|д|день|дня|дней))?" +
                "((\\d+)(h|hour|hours|ч|час|часа|часов))?" +
                "((\\d+)(min|mins|minute|minutes|мин|минута|минуту|минуты|минут))?" +
                "((\\d+)(s|sec|secs|second|seconds|с|c|сек|секунда|секунду|секунды|секунд))?$"
    )

    fun parseFull(value: String): Long {
        val m: Matcher = FULL_PATTERN.matcher(value)

        val values: MutableMap<FieldType, Int> = EnumMap(FieldType::class.java)
        while (m.find()) {
            val units = m.group(1).toInt()
            val type: FieldType? = FieldType.find(m.group(2))
            if (units == 0 || type == null) {
                return -1
            }
            if (values.containsKey(type)) {
                return -1
            }
            if (values.keys.stream().anyMatch { e: FieldType -> e.ordinal >= type.ordinal }) {
                return -1
            }
            values[type] = units
        }
        if (values.size > 1 && values.entries.stream().anyMatch { e: Map.Entry<FieldType, Int> -> e.value > e.key.maxUnits }) {
            return -1
        }

        val currentDate = Date()
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        values.forEach { (type: FieldType, units: Int) ->
            calendar.add(type.type, units)
        }
        return (if (values.isEmpty()) -1 else calendar.timeInMillis - currentDate.time)
    }

    fun parseShort(value: String): Long {
        val matcher = SHORT_PATTERN.matcher(value.lowercase())

        if (!matcher.matches()) throw IllegalArgumentException("Incorrect value for time parser: $value")

        var offsetDateTime = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC)

        offsetDateTime = addUnit(offsetDateTime, ChronoUnit.YEARS, matcher.group(2))
        offsetDateTime = addUnit(offsetDateTime, ChronoUnit.MONTHS, matcher.group(5))
        offsetDateTime = addUnit(offsetDateTime, ChronoUnit.WEEKS, matcher.group(8))
        offsetDateTime = addUnit(offsetDateTime, ChronoUnit.DAYS, matcher.group(11))
        offsetDateTime = addUnit(offsetDateTime, ChronoUnit.HOURS, matcher.group(14))
        offsetDateTime = addUnit(offsetDateTime, ChronoUnit.MINUTES, matcher.group(17))
        offsetDateTime = addUnit(offsetDateTime, ChronoUnit.SECONDS, matcher.group(20))

        return offsetDateTime.toEpochSecond(ZoneOffset.UTC) * 1000
    }

    private fun <T : Temporal> addUnit(instant: T, unit: ChronoUnit, amount: String): T {
        return if (StringUtils.isNumeric(amount)) unit.addTo(instant, amount.toLong()) else instant
    }
}