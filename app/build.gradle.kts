plugins {
    id("design.patterns.study.kotlin-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":audxo112"))
    implementation(project(":ezhoon"))
    implementation(project(":yangsooplus"))
    implementation(project(":ldh019"))
}

application {
    mainClass.set("design.patterns.study.app.AppKt")
}
