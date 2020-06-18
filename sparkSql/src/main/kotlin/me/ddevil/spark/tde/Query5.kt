package me.ddevil.spark.tde

import me.ddevil.spark.Project
import me.ddevil.spark.withCSV
import org.apache.spark.sql.SQLContext

import org.apache.spark.sql.functions

object Query5 : Project {
    override val name: String
        get() = "sql.5"

    override fun invoke(context: SQLContext) {
        context.withCSV("ommlbd_familiar") {
            val valid = where(functions.expr("FUNCIONARIOPUBLICOCASA == true")).count()
            println("${valid.toDouble() / count() * 100.00}%")
        }
    }
}