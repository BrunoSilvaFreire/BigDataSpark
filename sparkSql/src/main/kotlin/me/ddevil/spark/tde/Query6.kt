package me.ddevil.spark.tde

import me.ddevil.spark.Project
import me.ddevil.spark.withCSV
import org.apache.spark.sql.SQLContext

import org.apache.spark.sql.functions

object Query6 : Project {
    override val name: String
        get() = "sql.6"

    override fun invoke(context: SQLContext) {
        context.withCSV("ommlbd_regional") {
            val idh = col("IDHMUNICIPIO")
            val valid = where(idh.notEqual(-9999))
            val key = functions.floor(
                idh.divide(10)
            ).multiply(10).`as`("IDH")
            val allTotal = valid.count()
            valid.groupBy(key).also {
                it.count().apply {
                    withColumn(
                        "percentage",
                        col("count").divide(allTotal)
                    ).show()
                }
            }
        }
    }
}