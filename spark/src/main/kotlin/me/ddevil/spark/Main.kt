package me.ddevil.spark

import me.ddevil.spark.airports.AirportsAvg
import me.ddevil.spark.airports.AirportsLat
import me.ddevil.spark.airports.AirportsUSA
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.api.java.JavaSparkContext
import kotlin.system.measureTimeMillis

interface Project {
    val name: String
    operator fun invoke(sparkContext: JavaSparkContext)
}

val projects = listOf(
    AirportsUSA,
    AirportsLat,
    AirportsAvg
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
    val sc = JavaSparkContext(conf)
    for (project in toExecute) {
        println("Running project ${project.name}.")
        val ms = measureTimeMillis {
            project(sc)
        }
        println("Project ${project.name} took ${ms}ms.")
    }
}