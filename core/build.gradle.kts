plugins {
    java
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-netty:2.0.0")
    implementation("ch.qos.logback:logback-classic:1.2.7")
    implementation("com.zaxxer:HikariCP:4.0.3")
    implementation("org.jetbrains.exposed:exposed-core:0.37.3")
    implementation("org.jetbrains.exposed:exposed-dao:0.37.3")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.37.3")
    implementation("org.json:json:20211205")
    testImplementation("junit", "junit", "4.12")
}