rootProject.name = "bigData"
include(":spark", ":sparkThirdparty", ":sparkSql",":kotlinExtra")
pluginManagement {
    plugins {
        kotlin("jvm") version "1.3.72"
    }
}