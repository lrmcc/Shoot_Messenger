/*
 *  Shoot Messenger Library 0.0.1
 *  Luke McConnell
*/

// This should be altered as it is it intended to be a library.

import com.adarshr.gradle.testlogger.theme.ThemeType

version = "0.0.1"

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    id ("com.adarshr.test-logger") version "2.0.0"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:30.1.1-jre")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    // Define the main class for the application.
    mainClass.set("com.lukemcconnell.shoot.messenger.App")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.lukemcconnell.shoot.messenger.App"
    }
}

tasks.withType<JavaExec>() {
    standardInput = System.`in`
}