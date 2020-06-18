package me.ddevil.spark.tde

import me.ddevil.spark.Project
import me.ddevil.spark.withCSV
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions
object Query2 : Project {
    override val name: String
        get() = "sql.2"

    override fun invoke(context: SQLContext) {
        context.withCSV("ommlbd_basico") {
            select(functions.min(col("QTDEMAIL"))).show()
            select(functions.max(col("QTDEMAIL"))).show()
        }
    }
}