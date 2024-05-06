plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.slf4j:slf4j-api:1.7.26")
    implementation("org.slf4j:slf4j-simple:1.7.26")
    implementation("ai.djl.pytorch:pytorch-native-auto:1.8.1")
    implementation("ai.djl.pytorch:pytorch-model-zoo:0.11.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}