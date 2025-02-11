plugins {
    id("application")
    id("org.jetbrains.kotlin.jvm") version "1.8.10" // Asegúrate de usar tu versión
}

application {
    mainClass.set("MainKt")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("junit:junit:4.13.2")
}

kotlin {
    jvmToolchain(8)
}
