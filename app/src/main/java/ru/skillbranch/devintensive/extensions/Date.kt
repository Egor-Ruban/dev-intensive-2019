package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String="HH.mm.ss dd.MM.yy"):String{
    val dateformat = SimpleDateFormat(pattern, Locale("ru"))
    return dateformat.format(this)
}

fun Date.add(value:Int, units:String):Date{
    var time = this.time
    time += when(units){
        "seconds","second"->value* SECOND
        "minutes","minute"->value* MINUTE
        "hour","hours"->value* HOUR
        "day","days"->value* DAY
        else -> throw IllegalStateException("invalid unit")
    }
    this.time = time
    return this
}