package com.seungdols.dev.grpc.sample.hello

import com.seungdols.dev.grpc.configuration.FeignClientConfiguration
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "serverApiFeignClient", url = "https://localhost:8081", configuration = [FeignClientConfiguration::class])
interface ServerApiFeignClient {
    @GetMapping("/rest/sayhello")
    fun getHello(@RequestParam("name") name: String): String

    @GetMapping("/rest/megaData")
    fun getMegaData(): String
}
