import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.bootJar { enabled = false }
tasks.jar { enabled = true }

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    idea
}

group = "com.seungdols.dev.grpc.sample.support"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(Dependencies.GRPC)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${Versions.protobuf}"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${Versions.grpc}"
        }
        create("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${Versions.grpcKotlin}:jdk8@jar"
        }
    }
    // omitted protoc and plugins config
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
                create("grpckt")
            }
            it.builtins {
                create("kotlin")
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
    kotlinOptions.jvmTarget = "17"
}
