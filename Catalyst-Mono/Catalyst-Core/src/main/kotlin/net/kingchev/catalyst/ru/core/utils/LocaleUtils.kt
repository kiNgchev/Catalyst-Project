package net.kingchev.catalyst.ru.core.utils

import net.kingchev.catalyst.ru.core.model.CatalystLang
import java.util.*

object LocaleUtils {
    @JvmStatic
    val LANGS: HashMap<String, CatalystLang> = hashMapOf()

    val DEFAULT: CatalystLang

    init {
        for (lang in CatalystLang.entries) {
            LANGS[lang.language] = lang
        }
        DEFAULT = LANGS["en"]!!
    }

    fun get(locale: String): CatalystLang = LANGS[locale] ?: DEFAULT

    fun parse(input: String): CatalystLang {
        CatalystLang.entries.forEach {
            if (it.language.equals(input, ignoreCase = true)
                || it.code.equals(input, ignoreCase = true)
                || it.nativeName.equals(input, ignoreCase = true)
                || it.englishName.equals(input, ignoreCase = true)
                || it.other.contains(input)
            ) return it
        }

        return CatalystLang.EN_US
    }

    fun containsLang(input: String): Boolean {
        CatalystLang.entries.forEach {
            if (it.language.equals(input, ignoreCase = true)
                || it.code.equals(input, ignoreCase = true)
                || it.nativeName.equals(input, ignoreCase = true)
                || it.englishName.equals(input, ignoreCase = true)
                || it.other.contains(input)
            ) return true
        }

        return false
    }
}
