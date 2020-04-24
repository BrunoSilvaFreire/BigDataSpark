rootProject.name = "bigData"
include(":spark", ":sparkThirdparty", ":kotlinExtra")
pluginManagement {
    plugins {
        kotlin("jvm") version "1.3.72"
    }
}