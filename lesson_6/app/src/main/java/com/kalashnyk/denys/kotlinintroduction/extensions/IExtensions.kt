package com.kalashnyk.denys.kotlinintroduction.extensions

interface IExtensions {

    fun increment(a: Int): Int

    fun decrement(a: Int): Int

    fun sum(a: Int, b: Int): Int

    fun concat(str1: String, str2: String) = str1 + str2

    fun compareNumbers(a: Int, b: Int): String

    fun arrayFirstNumber(a: Array<Int>): String

    fun forCycle(a: Array<Int>): String

    fun whileCycle(a: Array<Int>): String

    fun whenOperator(a: Array<Int>): String
}