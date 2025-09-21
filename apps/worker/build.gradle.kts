plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    java
}

group = "dev.mateuszkowalczyk.order-worker"
version = "0.0.1-SNAPSHOT"
description = "Core of OrderSystem"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(project(":libs:order-model"))
    implementation(libs.validation)
    implementation(libs.web)
    implementation(libs.kafka)
    implementation(libs.mapstruct)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    annotationProcessor(libs.lombok.mapstruct.binding)
    annotationProcessor(libs.mapstruct.processor)
    testImplementation(libs.test)
    testImplementation(libs.kafka.test)
    testRuntimeOnly(libs.junit.platform.launcher)
}