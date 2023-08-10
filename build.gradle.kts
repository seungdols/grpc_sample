import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.bootJar { enabled = false }
tasks.jar { enabled = true }

plugins {
    id("org.springframework.boot")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.spring.dependency-management")
    id("com.google.protobuf")
    id("io.github.lognet.grpc-spring-boot")
    kotlin("kapt")
    kotlin("plugin.spring")
    kotlin("jvm")
    idea
}

version = "1.0-SNAPSHOT"
allprojects {
    apply {
        plugin("kotlin")
        plugin("kotlin-kapt")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("com.google.protobuf")
        plugin("idea")
    }
    group = "com.seungdols.dev.grpc.sample"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(Dependencies.KOTLIN)
        implementation(Dependencies.JACKSON)
        implementation(Dependencies.LOGGING)
        testImplementation(Dependencies.TEST)
    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        filter {
            exclude { it.file.path.contains("generated") }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
    kotlinOptions.jvmTarget = "17"
}
