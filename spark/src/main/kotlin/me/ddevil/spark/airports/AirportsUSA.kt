package me.ddevil.spark.airports

import me.ddevil.spark.Project
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.api.java.JavaSparkContext

object AirportsUSA : Project {
    override val name: String
        get() = "airportsUsa"

    override fun invoke(sparkContext: JavaSparkContext) {
        val airports = sparkContext.textFileToAirports("assets/airports.text")
        airports.foreachAsync {
            if (it.country == "\"United States\"") {
                println(it)
            }
        }
        println("Total of ${airports.count()} airports")
    }
}
