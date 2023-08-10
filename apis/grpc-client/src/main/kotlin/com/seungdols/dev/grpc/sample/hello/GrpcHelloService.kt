package com.seungdols.dev.grpc.sample.hello

import io.grpc.ManagedChannel
import io.grpc.examples.helloworld.GreeterGrpcKt
import io.grpc.examples.helloworld.HelloRequest
import io.grpc.examples.helloworld.Request
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder
import kotlinx.coroutines.runBlocking
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class GrpcHelloService(
    private val gRpcServerProperties: GRpcServerProperties
) {
    val certChain: Resource = gRpcServerProperties.security.certChain

    val channel: ManagedChannel = NettyChannelBuilder.forAddress("localhost", 6565)
        .useTransportSecurity()
        .sslContext(GrpcSslContexts.forClient().trustManager(certChain.inputStream).build()).build()

    fun sayHello(name: String): String {
        val stub = GreeterGrpcKt.GreeterCoroutineStub(channel)
        val request = HelloRequest.newBuilder().setName(name).build()
        return runBlocking {
            val response = stub.sayHello(request)
            return@runBlocking "${response.message}, congratulation!"
        }
    }

    fun megaData(): String {
        val stub = GreeterGrpcKt.GreeterCoroutineStub(channel)
        return runBlocking {
            val response = stub.megaData(
                Request.newBuilder()
                    .build()
            )
            return@runBlocking response.message
        }
    }
}
