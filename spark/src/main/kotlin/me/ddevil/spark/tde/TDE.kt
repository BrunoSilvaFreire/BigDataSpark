package me.ddevil.spark.tde

import org.apache.spark.api.java.JavaRDD
import org.apache.spark.api.java.JavaSparkContext
import java.io.File


fun JavaSparkContext.textFileToCommodity(file: String): JavaRDD<Commodity> {
    val jFile = File(file)
    if (!jFile.exists()) {
        throw IllegalStateException("Unable to find file ${jFile.absolutePath}. Please create it before running.")
    }
    val text = textFile(file)
    return text.map(Commodity.Companion::fromCSVLine)
}
