package com.kalashnyk.denys.kotlinintroduction.helpers

import android.widget.TextView

class ConsoleHelper(private val console: TextView) {

    fun append(string: Any) {
        if (string is String) {
            console.append(string + "\n")
        }
        else {
            console.append(string.toString() + "\n")
        }
    }

    fun clear() {
        console.text = ""
    }
}