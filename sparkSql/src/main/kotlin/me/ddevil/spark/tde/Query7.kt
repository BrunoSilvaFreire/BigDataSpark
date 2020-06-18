package me.ddevil.spark.tde

import me.ddevil.spark.Project
import me.ddevil.spark.loadCSV
import me.ddevil.spark.withCSV
import org.apache.spark.sql.SQLContext

import org.apache.spark.sql.functions
import org.apache.spark.sql.functions.col

object Query7 : Project {
    override val name: String
        get() = "sql.7"

    override fun invoke(context: SQLContext) {
        val renda = context.loadCSV("ommlbd_renda")
        val regional = context.loadCSV("ommlbd_basico")
//        SELEC
        with(renda.join(regional)) {
            println(
                where(
                    col("ESTIMATIVARENDA").gt(7000).and(
                        col("DISTCENTROCIDADE").lt(5000)
                    )
                ).count()
            )
        }
    }
}