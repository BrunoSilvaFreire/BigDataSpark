package me.ddevil.spark.airports

import me.ddevil.spark.Project
import org.apache.spark.api.java.JavaSparkContext

object AirportsAvg : Project {
    override val name: String
        get() = "airportsAvg"

    override fun invoke(sparkContext: JavaSparkContext) {
        val airports = sparkContext.textFileToAirports("assets/airports.text")
        val usaAirports = airports.filter { it.country == "\"United States\"" && it.lat > 40}
        usaAirports.saveAsTextFile("out/usaAirportsOverLat40")
    }
}
