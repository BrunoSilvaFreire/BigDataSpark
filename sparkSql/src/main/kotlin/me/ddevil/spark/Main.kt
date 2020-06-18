package me.ddevil.spark

import me.ddevil.spark.tde.*
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.api.java.JavaSparkContext
import org.apache.spark.sql.SQLContext
import kotlin.system.measureTimeMillis

interface Project {
    val name: String
    operator fun invoke(context: SQLContext)
}

val projects = listOf(
    Query1,
    Query2,
    Query3,
    Query4,
    Query5,
    Query6,
    Query7,
    Query8
)

fun main(args: Array<String>) {
    println("WorkDir: ${System.getProperty("user.dir")}")
    val projectName = args.joinToString(separator = " ")
    val toExecute: Iterable<Project>
    if (projectName == "--list") {

        println("Projects:")
        for (project in projects) {
            println("* '${project.name}' (To execute run: 'bin/spark ${project.name}').")
        }
        return
    }
    toExecute = if (projectName.isNotEmpty()) {
        projects.filter { it.name.startsWith(projectName) }
    } else {
        projects
    }

    println("Included projects:")
    for (project in toExecute) {
        println("* ${project.name}.")
    }
    println("Will begin running projects now.")
    Logger.getLogger("org").level = Level.ERROR
    val conf = SparkConf().setAppName("airport").setMaster("local[1]")
    val sc = SQLContext(JavaSparkContext(conf))
    for (project in toExecute) {
        println("Running project ${project.name}.")
        val ms = measureTimeMillis {
            project(sc)
        }
        println("Project ${project.name} took ${ms}ms.")
    }
}