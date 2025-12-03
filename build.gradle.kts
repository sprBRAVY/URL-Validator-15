plugins {
    java
    application
   checkstyle
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass.set("com.validator.App")
}

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5 для тестов
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.0")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

checkstyle {
    toolVersion = "8.23"

    configFile = file("checkstyle.xml")
}


tasks.named("checkstyleMain").configure { enabled = false }
tasks.named("checkstyleTest").configure { enabled = false }



tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}