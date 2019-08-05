package com.kalashnyk.denys.kotlinintroduction.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kalashnyk.denys.kotlinintroduction.helpers.ConsoleHelper
import com.kalashnyk.denys.kotlinintroduction.extensions.Extensions
import com.kalashnyk.denys.kotlinintroduction.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val prefix = "INFO"
    lateinit var console: ConsoleHelper
    lateinit var extensions: Extensions
    lateinit var arr: Array<Int>
    var number: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObjects()
        initClickListeners()
    }

    private fun initObjects() {
        console = ConsoleHelper(tvConsole)
        extensions = Extensions(prefix)
        number = Random.nextInt(0, 100)
        arr = arrayOf(1, 2, 3, Random.nextInt(0, 100), Random.nextInt(0, 100))
    }

    private fun initClickListeners() {
        btnClear.setOnClickListener {
            console.clear()
        }
        btnIncrement.setOnClickListener {
            console.append("Number = $number")
            number = extensions.increment(number)
            console.append("Now number = $number")
        }
        btnDecrement.setOnClickListener {
            console.append("Number = $number")
            number = extensions.decrement(number)
            console.append("Now number = $number")
        }
        btnSum.setOnClickListener {
            var someInt = Random.nextInt(0,100)
            console.append("Let's sum $number and $someInt")
            console.append(extensions.sum(number, someInt))
        }
        btnConcat.setOnClickListener {
            var someInt = Random.nextInt(0,100)
            console.append("Let's concat $number and $someInt")
            console.append(extensions.concat(number.toString(), someInt.toString()))
        }
        btnCompare.setOnClickListener {
            var someInt = Random.nextInt(0,100)
            console.append("Let's compare $number and $someInt")
            console.append(extensions.compareNumbers(number, someInt))
        }
        btnFirstOfArr.setOnClickListener {
            console.append(extensions.arrayFirstNumber(arr))
        }
        btnFor.setOnClickListener {
            console.append(extensions.forCycle(arr))
        }
        btnWhile.setOnClickListener {
            console.append(extensions.whileCycle(arr))
        }
        btnWhen.setOnClickListener {
            console.append(extensions.whenOperator(arr))
        }
    }
}
