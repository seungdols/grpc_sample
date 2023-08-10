rootProject.name = "grpc_sample"

include("support")
include("apis:grpc-service")
include("apis:grpc-client")

pluginManagement {
    val kotlinVersion = "1.9.0"
    val springBootVersion = "3.1.1"
    val springDependencyManagementVersion = "1.1.0"
    val ktlintVersion = "11.3.2"
    val protobufVersion = "0.9.3"
    val grpcSpringBootVersion = "5.1.3"

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.kapt" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "org.jlleitschuh.gradle.ktlint" -> useVersion(ktlintVersion)
                "org.jetbrains.kotlin.plugin.noarg" -> useVersion(kotlinVersion)
                "com.google.protobuf" -> useVersion(protobufVersion)
                "io.github.lognet.grpc-spring-boot" -> useVersion(grpcSpringBootVersion)
            }
        }
    }
}
