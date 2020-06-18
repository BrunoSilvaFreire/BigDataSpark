package me.ddevil.spark.tde

import me.ddevil.spark.Project
import me.ddevil.spark.withCSV
import org.apache.spark.sql.SQLContext

object Query1 : Project {
    override val name: String
        get() = "sql.1"

    override fun invoke(context: SQLContext) {
        context.withCSV("ommlbd_basico") {
            groupBy(col("ORIENTACAO_SEXUAL")).count().show()
        }
    }
}