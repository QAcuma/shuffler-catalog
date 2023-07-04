plugins {
    id("java-library")
    id("version-catalog")
    id("maven-publish")
}

group = "ru.acuma"
version = "2.0.5"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
}

catalog {
    versionCatalog {
        version("spring-boot-old", "2.7.8")
        version("spring-boot", "3.1.0")
        version("postgresql", "42.6.0")
        version("hibernate", "6.2.5.Final")
        version("flyway", "9.19.4")
        version("jooq", "3.17.8")
        version("lombok", "1.18.28")
        version("telegrambots", "6.7.0")
        version("junit", "5.9.3")
        version("assertj", "3.24.2")
        version("mockito", "4.5.1")
        version("gson", "2.9.0")
        version("lang3", "3.12.0")
        version("markdown", "1.3.2")
        version("mapstruct", "1.5.5.Final")
        version("mapstruct-lombok", "0.2.0")
        version("caffeine", "3.1.6")
        version("okhttp", "4.11.0")

        plugin("springframework", "org.springframework.boot").versionRef("spring-boot")
        plugin("flyway", "org.flywaydb.flyway").versionRef("flyway")

        library("spring-starter", "org.springframework.boot", "spring-boot-starter").versionRef("spring-boot")
        library("spring-web", "org.springframework.boot", "spring-boot-starter-web").versionRef("spring-boot")
        library("spring-test", "org.springframework.boot", "spring-boot-starter-test").versionRef("spring-boot")
        library("spring-security", "org.springframework.boot", "spring-boot-starter-security").versionRef("spring-boot")
        library("spring-aop", "org.springframework.boot", "spring-boot-starter-aop").versionRef("spring-boot")
        library("spring-redis", "org.springframework.boot", "spring-boot-starter-data-redis").versionRef("spring-boot")

        library("telegrambots", "org.telegram", "telegrambots").versionRef("telegrambots")
        library("telegrambotsextensions", "org.telegram", "telegrambotsextensions").versionRef("telegrambots")

        library("spring-jooq", "org.springframework.boot", "spring-boot-starter-jooq").versionRef("spring-boot")
        library("spring-data-jpa", "org.springframework.boot", "spring-boot-starter-data-jpa").versionRef("spring-boot")
        library("postgresql", "org.postgresql", "postgresql").versionRef("postgresql")
        library("flyway", "org.flywaydb", "flyway-core").versionRef("flyway")
        library("hibernate-jcache", "org.hibernate.orm", "hibernate-jcache").versionRef("hibernate")

        library("lombok", "org.projectlombok", "lombok").versionRef("lombok")

        library("junit", "org.junit.jupiter", "junit-jupiter-engine").versionRef("junit")
        library("assertj", "org.assertj", "assertj-core").versionRef("assertj")
        library("mockito", "org.mockito", "mockito-core").versionRef("mockito")
        library("okhttp", "com.squareup.okhttp3", "okhttp").versionRef("okhttp")

        library("caffeine", "com.github.ben-manes.caffeine", "caffeine").versionRef("caffeine")
        library("caffeine-jcache", "com.github.ben-manes.caffeine", "jcache").versionRef("caffeine")
        library("mapstruct", "org.mapstruct", "mapstruct").versionRef("mapstruct")
        library("mapstruct-processor", "org.mapstruct", "mapstruct-processor").versionRef("mapstruct")
        library("mapstruct-lombok", "org.projectlombok", "lombok-mapstruct-binding").versionRef("mapstruct-lombok")
        library("gson", "com.google.code.gson", "gson").versionRef("gson")
        library("lang3", "org.apache.commons", "commons-lang3").versionRef("lang3")
        library("markdown", "com.github.Steppschuh", "Java-Markdown-Generator").versionRef("markdown")

        bundle("data", listOf("postgresql", "flyway"))
        bundle("util", listOf("gson", "lang3", "markdown"))
        bundle("lombok", listOf("lombok"))
        bundle("telegram", listOf("telegrambots", "telegrambotsextensions"))
        bundle("test", listOf("spring-test", "junit", "mockito", "assertj"))
    }
}

publishing {
    repositories {
        maven {
            name = "shuffler-catalog"
            url = uri("https://maven.pkg.github.com/QAcuma")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
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