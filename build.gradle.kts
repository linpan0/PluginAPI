plugins {
    kotlin("jvm") version "1.5.20"
    `maven-publish`
}

group = "me.backword"
version = "1.0.0"

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:1.17-R0.1-SNAPSHOT")
}

tasks {
    compileKotlin {
        sourceCompatibility = JavaVersion.VERSION_16.toString()
        targetCompatibility = JavaVersion.VERSION_16.toString()
        kotlinOptions.jvmTarget = "16"
    }
}