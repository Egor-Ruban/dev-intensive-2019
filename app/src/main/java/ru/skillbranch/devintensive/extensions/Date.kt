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

    fun plural(value:Long):String{
        var typeOfPlural = 3
        if((value%100L)/10L!=1L && value%10L == 1L) typeOfPlural = 1
        else if((value%100L)/10L!=1L && value%10 >=2 && value%10 <=4) typeOfPlural = 2
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

fun Date.humanizeDiff(date:Date = Date()):String{
    val diffDate = date.time-this.time
    if(diffDate>=0) {
        if (diffDate >= 0 * TimeUnits.SECOND.time && diffDate <= 1 * TimeUnits.SECOND.time) {
            return "только что"
        } else if (diffDate >= 1 * TimeUnits.SECOND.time && diffDate <= 45 * TimeUnits.SECOND.time) {
            return "несколько секунд назад"
        } else if (diffDate >= 45 * TimeUnits.SECOND.time && diffDate <= 75 * TimeUnits.SECOND.time) {
            return "минуту назад"
        } else if (diffDate >= 75 * TimeUnits.SECOND.time && diffDate <= 45 * TimeUnits.MINUTE.time) {
            return "${TimeUnits.MINUTE.plural(diffDate / TimeUnits.MINUTE.time)} назад"
        } else if (diffDate >= 45 * TimeUnits.MINUTE.time && diffDate <= 75 * TimeUnits.MINUTE.time) {
            return "час назад"
        } else if (diffDate >= 75 * TimeUnits.MINUTE.time && diffDate <= 22 * TimeUnits.HOUR.time) {
            return "${TimeUnits.HOUR.plural(diffDate / TimeUnits.HOUR.time)} назад"
        } else if (diffDate >= 22 * TimeUnits.HOUR.time && diffDate <= 26 * TimeUnits.HOUR.time) {
            return "день назад"
        } else if (diffDate >= 26 * TimeUnits.HOUR.time && diffDate <= 360 * TimeUnits.DAY.time) {
            return "${TimeUnits.DAY.plural(diffDate / TimeUnits.DAY.time)} назад"
        } else {
            return "более года назад"
        }
    }
    else {
        if (-diffDate >= 0 * TimeUnits.SECOND.time && -diffDate <= 1 * TimeUnits.SECOND.time) {
            return "только что"
        } else if (-diffDate >= 1 * TimeUnits.SECOND.time && -diffDate <= 45 * TimeUnits.SECOND.time) {
            return "через несколько секунд"
        } else if (-diffDate >= 45 * TimeUnits.SECOND.time && -diffDate <= 75 * TimeUnits.SECOND.time) {
            return "через минуту"
        } else if (-diffDate >= 75 * TimeUnits.SECOND.time && -diffDate <= 45 * TimeUnits.MINUTE.time) {
            return "через ${TimeUnits.MINUTE.plural(-diffDate / TimeUnits.MINUTE.time)}"
        } else if (-diffDate >= 45 * TimeUnits.MINUTE.time && -diffDate <= 75 * TimeUnits.MINUTE.time) {
            return "через час"
        } else if (-diffDate >= 75 * TimeUnits.MINUTE.time && -diffDate <= 22 * TimeUnits.HOUR.time) {
            return "через ${TimeUnits.HOUR.plural(-diffDate / TimeUnits.HOUR.time)}"
        } else if (-diffDate >= 22 * TimeUnits.HOUR.time && -diffDate <= 26 * TimeUnits.HOUR.time) {
            return "через день"
        } else if (-diffDate >= 26 * TimeUnits.HOUR.time && -diffDate <= 360 * TimeUnits.DAY.time) {
            return "через ${TimeUnits.DAY.plural(-diffDate / TimeUnits.DAY.time)}"
        } else {
            return "более чем через год"
        }
    }
}