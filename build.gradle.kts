plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")

    implementation("com.toedter:jcalendar:1.4")

    implementation("com.microsoft.sqlserver:mssql-jdbc:11.2.3.jre17")

    // colored ui
    runtimeOnly("com.formdev:flatlaf:3.4")
    runtimeOnly("com.formdev:flatlaf-intellij-themes:3.4")

    implementation("org.apache.poi:poi:5.2.5")
    implementation("org.apache.poi:poi-ooxml:5.2.5")

//    compileOnly("org.projectlombok:lombok:1.18.24")
//    annotationProcessor("org.projectlombok:lombok:1.18.24")

}

java {
    targetCompatibility = JavaVersion.VERSION_17
    sourceCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass = "org.project1.nhom8.Main"
}

// tasks.named<Test>("test") {
//   useJUnitPlatform()
// }

tasks.compileJava {
    options.encoding = "UTF-8"
}