package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern


enum class TimeUnits(val time:Long){
    SECOND(1000L),
    MINUTE(60*SECOND.time),
    HOUR(60*MINUTE.time),
    DAY(24*HOUR.time)
}

fun Date.format(pattern: String="HH.mm.ss dd.MM.yy"):String{
    Thread.sleep(1000L)
    val dateformat = SimpleDateFormat(pattern, Locale("ru"))
    return dateformat.format(this)
}

fun Date.add(value:Int, units:TimeUnits):Date{
    var time = this.time
    time += when(units){
        TimeUnits.SECOND->value * TimeUnits.SECOND.time
        TimeUnits.MINUTE->value* TimeUnits.MINUTE.time
        TimeUnits.HOUR->value* TimeUnits.HOUR.time
        TimeUnits.DAY->value* TimeUnits.DAY.time
    }
    this.time = time
    return this
}