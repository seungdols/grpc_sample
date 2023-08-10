package com.seungdols.dev.grpc.sample.hello

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GrpcHelloController(
    val grpcHelloService: GrpcHelloService,
    val serverApiFeignClient: ServerApiFeignClient
) {
    @GetMapping("/greeting")
    fun greeting(@RequestParam(required = false, defaultValue = "") name: String): String {
        return grpcHelloService.sayHello(name)
    }

    @GetMapping("/megaData")
    fun getMegaData(): String {
        return grpcHelloService.megaData()
    }

    @GetMapping("/rest/greeting")
    fun restVersionGreeting(@RequestParam(required = false, defaultValue = "") name: String): String {
        return "${serverApiFeignClient.getHello(name)}, congratulation!"
    }

    @GetMapping("/rest/megaData")
    fun restVersionMegaData(): String {
        return serverApiFeignClient.getMegaData()
    }
}
