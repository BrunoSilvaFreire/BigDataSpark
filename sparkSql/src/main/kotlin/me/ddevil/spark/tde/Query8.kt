package me.ddevil.spark.tde

import me.ddevil.spark.Project
import me.ddevil.spark.loadCSV
import me.ddevil.spark.withCSV
import org.apache.spark.sql.SQLContext

import org.apache.spark.sql.functions
import org.apache.spark.sql.functions.col

object Query8 : Project {
    override val name: String
        get() = "sql.8"

    override fun invoke(context: SQLContext) {
        val renda = context.loadCSV("ommlbd_empresarial")
        val regional = context.loadCSV("ommlbd_renda")
        with(renda.join(regional)) {
            println(
                where(
                    col("ESTIMATIVARENDA").gt(5000).and(col("SOCIOEMPRESA").equalTo(true))
                ).count()
            )
        }
    }
}