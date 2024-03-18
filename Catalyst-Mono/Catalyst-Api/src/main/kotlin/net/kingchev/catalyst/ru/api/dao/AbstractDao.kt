package net.kingchev.catalyst.ru.api.dao

import net.kingchev.catalyst.ru.api.service.ConfigMapperService
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractDao {
    @Autowired
    protected lateinit var configMapper: ConfigMapperService
}