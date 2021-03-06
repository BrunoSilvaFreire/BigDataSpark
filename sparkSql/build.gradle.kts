plugins {
    kotlin("jvm") version "1.3.72"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":kotlinExtra"))
    api(kotlin("stdlib-jdk8"))
    api("org.apache.spark:spark-sql_2.12:3.0.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
application {
    mainClassName = "me.ddevil.spark.MainKt"
}
val installDist by tasks.getting(Sync::class)
val run by tasks.getting(JavaExec::class)
run.dependsOn(installDist)
run.workingDir = installDist.destinationDir

distributions {
    main {
        contents {
            from(file("assets")) {
                into("assets")
            }
            from("README.md")
            from(sourceSets.main.get().allSource) {
                into("src")
            }
        }
    }
}