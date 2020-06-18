package me.ddevil.spark

import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row
import org.apache.spark.sql.SQLContext
import java.io.File

inline fun SQLContext.withCSV(vararg fileName: String, block: Dataset<Row>.() -> Unit) {
    val csv = read().apply {
        format("csv")
        option("header", true)
    }
    with(
        csv.load(
            *fileName.map {
                File("assets/$it.csv").absolutePath
            }.toTypedArray()
        ), block
    )
}

inline fun SQLContext.withCSV(fileName: String, block: Dataset<Row>.() -> Unit) {
    with(loadCSV(fileName), block)
}

fun SQLContext.loadCSV(fileName: String): Dataset<Row> {
    val csv = read().apply {
        format("csv")
        option("header", true)
    }
    return csv.load(File("assets/$fileName.csv").absolutePath)
}