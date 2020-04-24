plugins {
    java
    distribution
}

repositories {
    mavenCentral()
}
dependencies {
    implementation("org.apache.spark:spark-core_2.11:2.4.3")
}
