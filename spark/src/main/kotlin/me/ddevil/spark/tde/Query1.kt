package me.ddevil.spark.tde

import me.ddevil.spark.Project
import org.apache.spark.api.java.JavaSparkContext

object Query1 : Project {
    override val name: String
        get() = "tde.q1"

    override fun invoke(sparkContext: JavaSparkContext) {
        sparkContext.textFileToCommodity("assets/transactions_min.csv")
            .filter {
                it.country == "Brazil"
            }
            .saveAsTextFile("out/result1")
    }

}