plugins {
    java
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-netty:2.0.0")
    implementation("ch.qos.logback:logback-classic:1.2.7")
    testImplementation("junit", "junit", "4.12")
}