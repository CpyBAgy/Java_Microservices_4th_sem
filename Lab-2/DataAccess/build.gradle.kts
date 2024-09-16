plugins {
    id("java")
}

group = "com.CpyBAgy.javarush"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("org.hibernate:hibernate-core-jakarta:5.6.15.Final")
}
