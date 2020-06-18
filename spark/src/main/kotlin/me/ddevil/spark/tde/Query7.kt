package me.ddevil.spark.tde

import me.ddevil.spark.Project
import org.apache.spark.api.java.JavaSparkContext
import java.io.File

object Query7 : Project {

    override val name: String
        get() = "tde.q7"

    override fun invoke(sparkContext: JavaSparkContext) {

        sparkContext.textFileToCommodity("assets/transactions.csv")
            .groupBy {
                CompositeKey(it.flux, it.year)
            }.countByKey().map {
                val (a, b) = it
                "${a.a} @ ${a.b}: $b"
            }.let {
                val f = File("out/q7")
                if (!f.exists()) {
                    f.parentFile.mkdirs()
                }
                f.writeText(it.joinToString(separator = System.lineSeparator()))
            }
    }
}