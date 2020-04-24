package me.ddevil.spark.airports

import me.ddevil.kotlinx.*
import org.apache.spark.api.java.JavaRDD
import org.apache.spark.api.java.JavaSparkContext
import java.io.Serializable

data class Airport(
    val id: Int,
    val name: String,
    val city: String,
    val country: String,
    val iata: String,
    val icao: String,
    val lat: Double,
    val long: Double
) {
    companion object : Serializable {
        fun fromCSVLine(line: String): Airport {
            val (id, name, city, country, iata, icao, lat, long) = line.split(',')
            return Airport(
                id.toInt(),
                name,
                city,
                country,
                iata,
                icao,
                lat.toDouble(),
                long.toDouble()
            )
        }
    }
}

fun JavaSparkContext.textFileToAirports(file: String): JavaRDD<Airport> {
    val text = textFile(file)
    return text.map(Airport.Companion::fromCSVLine)
}
