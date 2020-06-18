package me.ddevil.spark.tde

import me.ddevil.spark.Project
import org.apache.spark.api.java.JavaSparkContext

object Query3 : Project {
    override val name: String
        get() = "tde.q3"

    override fun invoke(sparkContext: JavaSparkContext) {

        sparkContext.textFileToCommodity("assets/transactions_min.csv")
            .filter {
                it.year == 2016 && it.flux == Commodity.Flux.IMPORT && it.country == "Brazil"
            }.groupBy {
                it.merchandise
            }.map { it }.sortBy({
                it._2.count()
            }, false, 1).saveAsTextFile("out/result3")
    }
}