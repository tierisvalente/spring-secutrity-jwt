package dev.tieris.springsecurity.controller

import dev.tieris.springsecurity.domain.MessageResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/hello")
class HelloWorldController {

    @GetMapping("/{name}")
    fun hello(@PathVariable("name") name: String): MessageResponse {
        return MessageResponse("Hello ${name}!", LocalDateTime.now())
    }
}