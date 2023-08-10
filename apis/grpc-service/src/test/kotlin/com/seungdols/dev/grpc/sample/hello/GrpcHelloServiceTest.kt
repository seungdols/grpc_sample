package com.seungdols.dev.grpc.sample.hello

import com.seungdols.dev.grpc.GrpcServerApiApplication
import io.grpc.ManagedChannel
import io.grpc.examples.helloworld.GreeterGrpcKt
import io.grpc.examples.helloworld.HelloRequest
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.testing.GrpcCleanupRule
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.string.shouldContain
import kotlinx.coroutines.runBlocking
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@SpringBootTest(
    classes = [GrpcServerApiApplication::class]
)
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class GrpcHelloServiceTest(
    val grpcHelloService: GrpcHelloService
) : AnnotationSpec() {
    val grpcCleanup: GrpcCleanupRule = GrpcCleanupRule()
    private val serverName = InProcessServerBuilder.generateName()

    @Test
    fun `GrpcHelloService가 정상적으로 bean 등록이 되면, null이 아니다`() {
        grpcHelloService.shouldNotBeNull()
    }

    @Test
    fun `sayHello 요청을 보내고, 응답을 받아서 의도 했던 값이 있는지 체크한다`() {
        grpcCleanup.register(
            InProcessServerBuilder
                .forName(serverName).directExecutor().addService(grpcHelloService).build().start()
        )
        val channel: ManagedChannel = grpcCleanup.register(
            InProcessChannelBuilder
                .forName(serverName).directExecutor().build()
        )

        val stub = GreeterGrpcKt.GreeterCoroutineStub(channel)
        val given = "seungdols"
        val req =
            HelloRequest.newBuilder()
                .apply {
                    name = given
                }
                .build()

        runBlocking {
            val reply = stub.sayHello(req)
            reply.message.shouldContain(given)
        }
    }
}
