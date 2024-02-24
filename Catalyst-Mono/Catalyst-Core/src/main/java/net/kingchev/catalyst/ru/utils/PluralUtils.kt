package net.kingchev.catalyst.ru.utils

import java.util.*
import java.util.regex.Pattern

object PluralUtils {
    private var pluralRules: MutableMap<String, Map<String, Pattern>>

    init {
        var ruRules: MutableMap<String, Pattern> = LinkedHashMap()
        ruRules["zero"] = Pattern.compile("^\\d*0$")
        ruRules["one"] = Pattern.compile("^(-?\\d*[^1])?1$")
        ruRules["two"] = Pattern.compile("^(-?\\d*[^1])?2$")
        ruRules["few"] = Pattern.compile("(^(-?\\d*[^1])?3)|(^(-?\\d*[^1])?4)$")
        ruRules["many"] = Pattern.compile("^\\d+$")
        ruRules = Collections.unmodifiableMap(ruRules)

        var enRules: MutableMap<String, Pattern> = LinkedHashMap()
        enRules["zero"] = Pattern.compile("^0$")
        enRules["one"] = Pattern.compile("^1$")
        enRules["other"] = Pattern.compile("^\\d+$")
        enRules = Collections.unmodifiableMap(enRules)

        val rules: MutableMap<String, Map<String, Pattern>> = HashMap()
        rules[LocaleUtils.RU_LOCALE] = ruRules
        rules[Locale.ENGLISH.language] = enRules

        pluralRules = rules
    }

    @JvmStatic
    fun getPluralKey(locale: Locale, value: Long): String {
        val str = value.toString()
        var key = "other"

        var rules = pluralRules[locale.language]
        if (rules == null) {
            rules = pluralRules[Locale.ENGLISH.language]
        }

        for ((key1, value1) in rules!!) {
            if (value1.matcher(str).find()) {
                key = key1
                break
            }
        }
        return key
    }
}