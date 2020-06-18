package me.ddevil.spark.tde

import me.ddevil.spark.Project
import org.apache.spark.api.java.JavaSparkContext

object Query2 : Project {
    override val name: String
        get() = "tde.q2"

    override fun invoke(sparkContext: JavaSparkContext) {

        sparkContext.textFileToCommodity("assets/transactions_min.csv")
            .groupBy {
                it.year
            }.mapValues {
                it.count()
            }.saveAsTextFile("out/result2")
    }
}