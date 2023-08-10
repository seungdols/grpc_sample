package com.seungdols.dev.grpc.configuration

import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {
    @Bean
    fun gRpcServerProperties(): GRpcServerProperties {
        return GRpcServerProperties()
    }
}
