
plugins {
    java
    application
}


repositories {
    mavenCentral()
}

dependencies {

    implementation("com.toedter:jcalendar:1.4")

    implementation("com.microsoft.sqlserver:mssql-jdbc:11.2.3.jre17")

    // colored ui
    runtimeOnly("com.formdev:flatlaf:3.4")
    runtimeOnly("com.formdev:flatlaf-intellij-themes:3.4")

    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

}

java {
    targetCompatibility = JavaVersion.VERSION_17
    sourceCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass = "org.project1.nhom8.Main"
}