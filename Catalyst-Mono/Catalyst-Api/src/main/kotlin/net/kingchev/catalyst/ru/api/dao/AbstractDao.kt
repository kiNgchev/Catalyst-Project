package net.kingchev.catalyst.ru.api.dao

import net.kingchev.catalyst.ru.api.service.MapperService
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractDao {
    @Autowired
    protected lateinit var mapper: MapperService
}