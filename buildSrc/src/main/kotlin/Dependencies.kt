import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    val KOTLIN = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    )

    val KOTLIN_COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutine}"

    val SPRING_WEB = listOf(
        "org.springframework.boot:spring-boot-starter-web",
        "org.springframework.boot:spring-boot-starter-validation",
        "org.springframework.data:spring-data-commons",
    )

    val FEIGN = "org.springframework.cloud:spring-cloud-starter-openfeign:${Versions.feign}"

    val GRPC = listOf(
        "com.google.protobuf:protobuf-kotlin:${Versions.protobuf}",
        "io.grpc:grpc-protobuf:${Versions.grpc}",
        "io.grpc:grpc-kotlin-stub:${Versions.grpcKotlin}"
    )

    val SPRING_GRPC = listOf(
        "io.github.lognet:grpc-spring-boot-starter:${Versions.grpcSpringBootStarter}"
    )

    val JACKSON = listOf(
        "com.fasterxml.jackson.module:jackson-module-kotlin",
        "org.jetbrains.kotlin:kotlin-reflect",
        "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.4"
    )

    val TEST = listOf(
        "org.springframework.boot:spring-boot-starter-test",
        "org.jetbrains.kotlin:kotlin-test",
        // kotest
        "io.kotest:kotest-runner-junit5:${Versions.kotest}",
        "io.kotest:kotest-assertions-core:${Versions.kotest}",
        "io.kotest.extensions:kotest-extensions-spring:${Versions.kotestExtensionSpring}",
        // mockk
        "io.mockk:mockk:${Versions.mockk}",
        "com.ninja-squad:springmockk:${Versions.springMockk}",
        "io.grpc:grpc-testing:${Versions.grpc}"
    )
    const val MYSQL = "mysql:mysql-connector-java:${Versions.mysqlConnector}"
    const val LOGGING = "io.github.microutils:kotlin-logging-jvm:2.1.23"
}

fun DependencyHandler.api(dependencies: List<Any>) {
    dependencies.forEach {
        add("api", it)
    }
}

fun DependencyHandler.implementation(dependencies: List<Any>) {
    dependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.compileOnly(dependencies: List<Any>) {
    dependencies.forEach {
        add("compileOnly", it)
    }
}

fun DependencyHandler.testImplementation(dependencies: List<String>) {
    dependencies.forEach {
        add("testImplementation", it)
    }
}

fun DependencyHandler.kapt(dependencies: List<String>) {
    dependencies.forEach {
        add("kapt", it)
    }
}
