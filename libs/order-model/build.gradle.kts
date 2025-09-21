plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    java
}

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
    implementation(libs.mongodb)
    implementation(libs.validation)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    testImplementation(libs.test)
    testImplementation(libs.kafka.test)
    testRuntimeOnly(libs.junit.platform.launcher)
}