rootProject.name = "OrderSystem"

include(":apps:core")
include(":apps:worker")
include(":libs:order-model")

dependencyResolutionManagement {
    versionCatalogs {
        create("projects") {
//            project(":libs:orderdb")
            library("order-model", ":libs:order-model")
        }

        create("libs") {
            library("mongodb", "org.springframework.boot", "spring-boot-starter-data-mongodb").withoutVersion()
            library("validation", "org.springframework.boot", "spring-boot-starter-validation").withoutVersion()
            library("web", "org.springframework.boot", "spring-boot-starter-web").withoutVersion()
            library("kafka", "org.springframework.kafka", "spring-kafka").withoutVersion()
            library("test", "org.springframework.boot", "spring-boot-starter-test").withoutVersion()
            library("kafka-test", "org.springframework.kafka", "spring-kafka-test").withoutVersion()
            library("lombok", "org.projectlombok", "lombok").withoutVersion()
            library("lombok-mapstruct-binding", "org.projectlombok", "lombok-mapstruct-binding").version("0.2.0")
            library("junit-platform-launcher", "org.junit.platform", "junit-platform-launcher").withoutVersion()
            library("mapstruct", "org.mapstruct", "mapstruct").version("1.6.3")
            library("mapstruct-processor", "org.mapstruct", "mapstruct-processor").version("1.6.3")
        }
    }
}