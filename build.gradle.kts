plugins {
    id("java-library")
    id("version-catalog")
    id("maven-publish")
}

group = "ru.acuma"
version = "2.0.0"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
}

catalog {
    versionCatalog {
        version("shuffler-lib", project.version as String)
        version("spring-boot", "2.7.3")
        version("postgresql", "42.3.4")
        version("flyway", "8.5.10")
        version("jooq", "3.16.6")
        version("lombok", "1.18.24")
        version("telegrambots", "6.0.1")
        version("junit", "5.8.2")
        version("mockito", "4.5.1")
        version("orika", "2.2.7")
        version("gson", "2.9.0")
        version("lang3", "3.12.0")
        version("markdown", "1.3.2")

        plugin("springframework", "org.springframework.boot").versionRef("spring-boot")

        library("shuffler-lib", "ru.acuma", "shuffler-lib").versionRef("shuffler-lib")

        library("spring-starter", "org.springframework.boot", "spring-boot-starter").versionRef("spring-boot")
        library("spring-web", "org.springframework.boot", "spring-boot-starter-web").versionRef("spring-boot")
        library("spring-test", "org.springframework.boot", "spring-boot-starter-test").versionRef("spring-boot")
        library("spring-security", "org.springframework.boot", "spring-boot-starter-security").versionRef("spring-boot")

        library("telegrambots", "org.telegram", "telegrambots").versionRef("telegrambots")
        library("telegrambotsextensions", "org.telegram", "telegrambotsextensions").versionRef("telegrambots")

        library("spring-jooq", "org.springframework.boot", "spring-boot-starter-jooq").versionRef("spring-boot")
        library("postgresql", "org.postgresql", "postgresql").versionRef("postgresql")
        library("flyway", "org.flywaydb", "flyway-core").versionRef("flyway")
        library("jooq", "org.jooq", "jooq").versionRef("jooq")

        library("lombok", "org.projectlombok", "lombok").versionRef("lombok")

        library("junit", "org.junit.jupiter", "junit-jupiter-engine").versionRef("lombok")
        library("mockito", "org.mockito", "mockito-core").versionRef("mockito")

        library("orika", "dev.akkinoc.spring.boot", "orika-spring-boot-starter").versionRef("orika")
        library("gson", "com.google.code.gson", "gson").versionRef("gson")
        library("lang3", "org.apache.commons", "commons-lang3").versionRef("lang3")
        library("markdown", "com.github.Steppschuh", "Java-Markdown-Generator").versionRef("markdown")

        bundle("data", listOf("spring-jooq", "postgresql", "flyway"))
        bundle("util", listOf("orika", "gson", "lang3", "markdown"))
        bundle("lombok", listOf("lombok"))
        bundle("telegram", listOf("telegrambots", "telegrambotsextensions"))
        bundle("test", listOf("spring-test", "junit", "mockito"))
    }
}

publishing {
    repositories {
        maven {
            name = "shuffler-catalog"
            url = uri("https://maven.pkg.github.com/QAcuma/shuffler-catalog")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("shuffler-catalog") {
            artifactId = "shuffler-catalog"
            from(components["versionCatalog"])
        }
    }
}