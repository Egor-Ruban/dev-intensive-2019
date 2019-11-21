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
    DAY(24*HOUR.time);

    fun plural(value:Int):String{
        var typeOfPlural = 3
        if((value%100)/10!=1 && value%10 == 1) typeOfPlural = 1
        else if((value%100)/10!=1 && value%10 >=2 && value%10 <=4) typeOfPlural = 2
        if(this == SECOND){
            when(typeOfPlural){
                1 -> return "$value секунду"
                2 -> return "$value секунды"
                3 -> return "$value секунд"
            }
        }
        if(this == MINUTE){
            when(typeOfPlural){
                1 -> return "$value минуту"
                2 -> return "$value минуты"
                3 -> return "$value минут"
            }
        }
        if(this == HOUR){
            when(typeOfPlural){
                1 -> return "$value час"
                2 -> return "$value часа"
                3 -> return "$value часов"
            }
        }
        when(typeOfPlural){
            1 -> return "$value день"
            2 -> return "$value дня"
            3 -> return "$value дней"
        }
        return "error"
    }
}

fun Date.format(pattern: String="HH:mm:ss dd.MM.yy"):String{
    Thread.sleep(1000L)
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)

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