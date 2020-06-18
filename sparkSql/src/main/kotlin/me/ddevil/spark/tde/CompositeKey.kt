package me.ddevil.spark.tde

import java.io.Serializable

data class CompositeKey<A, B>(
    val a: A,
    val b: B
) : Serializable, Comparable<CompositeKey<A, B>> where A : Comparable<A>, B : Comparable<B> {
    override fun compareTo(other: CompositeKey<A, B>): Int {
        val yearComparison = b.compareTo(other.b)
        if (yearComparison != 0) {
            return yearComparison
        }
        return a.compareTo(other.a)
    }
}