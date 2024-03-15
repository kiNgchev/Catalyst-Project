package net.kingchev.catalyst.ru.core.model

import java.nio.charset.Charset
import java.util.*

enum class CatalystLang(
    var language: String,
    var country: String,
    nativeName: String,
    var englishName: String,
    vararg val other: String
) {
    EN_US("en", "US", "English", "English"),
    RU_RU("ru", "RU", "Русский", "Russian", "cyka blyat", "cheeki breeki", "rush b");

    val nativeName = String(nativeName.toByteArray(), Charset.forName("UTF-8"))
    val locale: Locale = Locale(language, country)
    val code: String = language + "_" + country
}
