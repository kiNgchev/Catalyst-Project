package net.kingchev.catalyst.ru.controller.base

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController("baseDashboardController")
@RequestMapping("/dashboard")
abstract class BaseDashboardController : BaseRestController() {
}