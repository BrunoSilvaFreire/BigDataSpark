package me.ddevil.spark.tde

import me.ddevil.spark.Project
import org.apache.spark.api.java.JavaSparkContext

object Query5 : Project {

    override val name: String
        get() = "tde.q5"

    override fun invoke(sparkContext: JavaSparkContext) {

        sparkContext.textFileToCommodity("assets/transactions_min.csv")
            .filter {
                it.country == "Brazil"
            }.groupBy {
                CompositeKey(it.merchandise, it.year)
            }.mapValues {
                val sum = it.sumByDouble { it.weight }
                sum / it.count()
            }.saveAsTextFile("out/result5")
    }
}