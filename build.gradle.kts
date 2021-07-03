plugins {
    kotlin("jvm") version "1.5.20"
    `maven-publish`
}

group = "me.backword"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:1.17-R0.1-SNAPSHOT")
}

publishing {
    publications {
        register<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "PluginAPI"
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