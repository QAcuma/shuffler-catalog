plugins {
    id("java-library")
    id("version-catalog")
    id("maven-publish")
}

group = "ru.acuma"
version = "2.0.1"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
}

catalog {
    versionCatalog {
        version("spring-boot", "2.7.3")
        version("postgresql", "42.3.4")
        version("flyway", "8.5.10")
        version("jooq", "3.16.6")
        version("lombok", "1.18.24")
        version("telegrambots", "6.0.1")
        version("junit", "5.8.2")
        version("mockito", "4.5.1")
        version("gson", "2.9.0")
        version("lang3", "3.12.0")
        version("markdown", "1.3.2")
        version("mapstruct", "1.5.3.Final")
        version("mapstruct-lombok", "0.2.0")

        plugin("springframework", "org.springframework.boot").versionRef("spring-boot")

        library("spring-starter", "org.springframework.boot", "spring-boot-starter").versionRef("spring-boot")
        library("spring-web", "org.springframework.boot", "spring-boot-starter-web").versionRef("spring-boot")
        library("spring-test", "org.springframework.boot", "spring-boot-starter-test").versionRef("spring-boot")
        library("spring-security", "org.springframework.boot", "spring-boot-starter-security").versionRef("spring-boot")
        library("spring-aop", "org.springframework.boot", "spring-boot-starter-aop").versionRef("spring-boot")

        library("telegrambots", "org.telegram", "telegrambots").versionRef("telegrambots")
        library("telegrambotsextensions", "org.telegram", "telegrambotsextensions").versionRef("telegrambots")

        library("spring-jooq", "org.springframework.boot", "spring-boot-starter-jooq").versionRef("spring-boot")
        library("postgresql", "org.postgresql", "postgresql").versionRef("postgresql")
        library("flyway", "org.flywaydb", "flyway-core").versionRef("flyway")
        library("jooq", "org.jooq", "jooq").versionRef("jooq")

        library("lombok", "org.projectlombok", "lombok").versionRef("lombok")

        library("junit", "org.junit.jupiter", "junit-jupiter-engine").versionRef("lombok")
        library("mockito", "org.mockito", "mockito-core").versionRef("mockito")

        library("mapstruct", "org.mapstruct", "mapstruct").versionRef("mapstruct")
        library("mapstruct-processor", "org.mapstruct", "mapstruct-processor").versionRef("mapstruct")
        library("mapstruct-lombok", "org.projectlombok", "lombok-mapstruct-binding").versionRef("mapstruct-lombok")
        library("gson", "com.google.code.gson", "gson").versionRef("gson")
        library("lang3", "org.apache.commons", "commons-lang3").versionRef("lang3")
        library("markdown", "com.github.Steppschuh", "Java-Markdown-Generator").versionRef("markdown")

        bundle("data", listOf("spring-jooq", "postgresql", "flyway"))
        bundle("util", listOf("gson", "lang3", "markdown"))
        bundle("lombok", listOf("lombok"))
        bundle("telegram", listOf("telegrambots", "telegrambotsextensions"))
        bundle("test", listOf("spring-test", "junit", "mockito"))
    }
}

publishing {
    publications {
        create<MavenPublication>("shuffler-catalog") {
            artifactId = "shuffler-catalog"
            from(components["versionCatalog"])
        }
    }
}