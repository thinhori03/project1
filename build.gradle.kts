plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.itextpdf:html2pdf:5.0.3")

    implementation("org.jfree:jfreechart:1.5.4")

    implementation("com.itextpdf:itextpdf:5.5.13.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")

    implementation("com.toedter:jcalendar:1.4")

    implementation("com.microsoft.sqlserver:mssql-jdbc:11.2.3.jre17")

    // colored ui
    runtimeOnly("com.formdev:flatlaf:3.4")
    runtimeOnly("com.formdev:flatlaf-intellij-themes:3.4")

    // export/import excel
    implementation("org.apache.poi:poi:5.2.5")
    implementation("org.apache.poi:poi-ooxml:5.2.5")

    // mail sender
    implementation("org.eclipse.angus:angus-mail:2.0.3")
    implementation("jakarta.mail:jakarta.mail-api:2.1.3")

    // mail content template
    implementation("org.thymeleaf:thymeleaf:3.1.2.RELEASE")
}

java {
    targetCompatibility = JavaVersion.VERSION_17
    sourceCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass = "org.project1.nhom8.Main"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.compileJava {
    options.encoding = "UTF-8"
}