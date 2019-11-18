package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false
) {

    companion object Factory{
        var lastId = -1
        fun makeUser(fullName:String?) : User{
            val (firstName,secondName) = Utils.parseFullName(fullName)
            //print("$firstName and $secondName \n")
            lastId++
            return User(lastId.toString(),firstName,secondName,null)
        }
    }
}