package com.seungdols.dev.grpc.sample.hello

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/rest/sayhello")
    fun hello(@RequestParam name: String): String {
        return "Hello, REST world! $name!"
    }

    @GetMapping("/rest/megaData")
    fun getMegaData(): String {
        return generate1MBString()
    }
}
