package me.ddevil.spark.tde

import me.ddevil.spark.Project
import me.ddevil.spark.withCSV
import org.apache.spark.sql.SQLContext

import org.apache.spark.sql.functions
object Query4 : Project {
    override val name: String
        get() = "sql.4"

    override fun invoke(context: SQLContext) {
        context.withCSV("ommlbd_renda") {
            println(where(col("BOLSAFAMILIA").equalTo(true)).count())
        }
    }
}