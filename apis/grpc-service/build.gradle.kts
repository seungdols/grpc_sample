import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.bootJar { enabled = true }
tasks.jar { enabled = false }

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    idea
}

group = "com.seungdols.dev.grpc.sample.grpc-service"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(kotlin("reflect"))
    implementation(Dependencies.KOTLIN_COROUTINE)
    implementation(Dependencies.SPRING_WEB)
    implementation(Dependencies.SPRING_GRPC)
    implementation(Dependencies.GRPC)

    implementation(project(":support"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
    kotlinOptions.jvmTarget = "17"
}
