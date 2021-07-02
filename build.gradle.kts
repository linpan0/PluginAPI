plugins {
    kotlin("jvm") version "1.5.20"
    `maven-publish`
}

group = "me.backword"
version = "1.0.2"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:1.17-R0.1-SNAPSHOT")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/bqckword/pluginapi")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        register<MavenPublication>("gpr") {
            groupId = project.group.toString()
            artifactId = "pluginapi"
            version = project.version.toString()

            from(components["kotlin"])
        }
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "16"
    }
}