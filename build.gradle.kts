import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  kotlin("jvm") version "2.0.0"
  `maven-publish`
}

group = "me.backword"
version = "4"

repositories {
  mavenCentral()
  maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
  implementation(kotlin("stdlib"))
  compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
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

kotlin {
  compilerOptions {
    jvmTarget.set(JvmTarget.JVM_21)
  }
}

java {
  toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}