package com.seungdols.dev.grpc.sample.hello

import io.grpc.examples.helloworld.GreeterGrpc
import io.grpc.examples.helloworld.HelloReply
import io.grpc.examples.helloworld.HelloRequest
import io.grpc.examples.helloworld.Request
import io.grpc.examples.helloworld.Response
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class GrpcHelloService : GreeterGrpc.GreeterImplBase() {
    override fun sayHello(request: HelloRequest, responseObserver: StreamObserver<HelloReply>) {
        val name = request.name
        val response = HelloReply.newBuilder().setMessage("Hello, GRPC world! $name!").build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun megaData(request: Request, responseObserver: StreamObserver<Response>) {
        val response = Response.newBuilder().setMessage(generate1MBString()).build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}
