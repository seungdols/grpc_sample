package com.seungdols.dev.grpc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class GrpcClientApiApplication

fun main() {
    runApplication<GrpcClientApiApplication>()
}
