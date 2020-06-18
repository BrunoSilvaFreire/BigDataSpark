package me.ddevil.spark.tde

import me.ddevil.kotlinx.*
import me.ddevil.spark.airports.Airport
import java.io.Serializable

data class Commodity(
    val country: String,
    val year: Int,
    val code: Int,
    val merchandise: String,
    val flux: Flux,
    val value: Double,
    val weight: Double,
    val unit: String,
    val quantity: Int,
    val category: String
) : Serializable {
    enum class Flux(
        val id: String
    ) {
        IMPORT("IMPORT"),
        EXPORT("EXPORT"),
        REEXPORT("RE-EXPORT"),
        REIMPORT("RE-IMPORT");

        companion object {
            fun byId(id: String) = values().first { it.id == id }
        }
    }

    companion object : Serializable {
        fun fromCSVLine(line: String): Commodity {
            //country_or_area;year;comm_code;commodity;flow;trade_usd;weight_kg;quantity_name;quantity;category
            val elements = line.split(';')
            try {
                val (country, year, code, commodity, flow, value, weight, unit, quantity, category) = elements
                return Commodity(
                    country,
                    year.toInt(),
                    code.toInt(),
                    commodity,
                    Flux.byId(flow.toUpperCase()),
                    value.toDouble(),
                    weight.toDoubleOrNull() ?: 0.0,
                    unit,
                    quantity.toIntOrNull() ?: 0,
                    category
                )
            } catch (e: Exception) {
                println("Error while loading line $line")
                println(
                    "Elements are: [${elements.joinToString(separator = "|")}]"
                )
                throw e
            }

        }
    }
}