package net.kingchev.catalyst.ru.core.localezied.service.impl

import net.kingchev.catalyst.ru.core.localezied.CatalystMessageSource
import net.kingchev.catalyst.ru.core.localezied.service.LocaleService
import net.kingchev.catalyst.ru.core.utils.LocaleUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.NoSuchMessageException
import org.springframework.stereotype.Service
import java.util.Locale

@Service
class LocaleServiceImpl : LocaleService {
    @Autowired
    private lateinit var messageSource: CatalystMessageSource
    override fun getMessage(key: String, locale: String): String {
        return try {
            messageSource.getMessage(key, null, LocaleUtils.get(locale).locale)
        } catch (_: NoSuchMessageException) {
            key
        }
    }

    override fun getMessage(key: String, locale: String, vararg objects: Any): String {
        return try {
            messageSource.getMessage(key, objects, LocaleUtils.get(locale).locale).format(objects)
        } catch (_: NoSuchMessageException) {
            key
        }
    }
}