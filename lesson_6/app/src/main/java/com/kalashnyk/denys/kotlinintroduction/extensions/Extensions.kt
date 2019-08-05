package com.kalashnyk.denys.kotlinintroduction.extensions

class Extensions(var prefix: String): IExtensions {

    init {
        prefix = "[$prefix]"
    }

    override fun increment(a: Int): Int {
        return a + 1
    }

    override fun decrement(a: Int): Int {
        return a - 1
    }

    override fun sum(a: Int, b: Int): Int {
        return a + b
    }

    override fun concat(str1: String, str2: String) = prefix + str1 + str2

    override fun compareNumbers(a: Int, b: Int): String {
        var result: String
        if (a > b) {
            result = "$prefix First number is bigger than second."
        } else if (a == b) {
            result = "$prefix Numbers are the same."
        } else {
            result = "$prefix Second number is bigger than first."
        }
        return result
    }

    override fun arrayFirstNumber(a: Array<Int>): String {
        return "$prefix First argument is ${a[0]}"
    }

    override fun forCycle(a: Array<Int>): String {
        var result = "$prefix "
        for (i in 0 until a.size) {
            result += "${a[i]} "
        }
        return result
    }

    override fun whileCycle(a: Array<Int>): String {
        var result = "$prefix "
        var i = 0
        while (i < a.size) {
            result += "${a[i]} "
            i++
        }
        return result
    }

    override fun whenOperator(a: Array<Int>): String {
        var result = "$prefix "
        for (i in 0 until a.size) {
            when (a[i]) {
                1 -> result += "one "
                2 -> result += "to "
                3 -> result += "three "
                else -> result += "and random "
            }
        }
        return result
    }
}

