/*
 *  Shoot Messenger Client 0.0.1
 *  Luke McConnell
*/

import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
    java
    application // Apply the application plugin for CLI application support in Java.
    id ("com.adarshr.test-logger") version "2.0.0"
    id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.1.0"
}

version = "0.0.1"
group = "com.lukemcconnell"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")

    // This dependency is a set of Google Java core libraries.
    implementation("com.google.guava:guava:30.1.1-jre")

    // The following list applicable Spring depedencies
    implementation("org.springframework.boot:spring-boot-starter-amqp")
	// implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	// implementation("org.springframework.boot:spring-boot-starter-websocket")
	// implementation("org.springframework.session:spring-session-core")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.amqp:spring-rabbit-test")
	// testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    // Define the main class for the application.
    mainClass.set("com.lukemcconnell.shoot.client.App")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.lukemcconnell.shoot.client.App"
    }
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
   this.archiveFileName.set("shoot-app.jar")
}

tasks.withType<JavaExec>() {
    standardInput = System.`in`
}