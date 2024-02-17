package net.kingchev.catalyst.ru.controller.base

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController("baseUserController")
@RequestMapping("/user")
abstract class BaseUserController : BaseRestController() {
}