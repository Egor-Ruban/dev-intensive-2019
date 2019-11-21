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

    data class Builder(var id : String = "0",
                  var firstName : String? = null,
                  var lastName : String? = null,
                  var avatar : String? = null,

                  var rating : Int = 0,
                  var respect : Int = 0,
                  var lastVisit : Date? = Date(),
                  var isOnline : Boolean = false
    ){
        fun id(id:String):Builder{
            this.id = id
            return this
        }
        fun firstName(firstName: String?):Builder{
            this.firstName=firstName
            return this
        }

        fun lastName(lastName: String?):Builder{
            this.lastName = lastName
            return this
        }

        fun avatar(avatar: String?):Builder{
            this.avatar = avatar
            return this
        }

        fun rating(rating:Int):Builder{
            this.rating = rating
            return this
        }

        fun respect(respect: Int):Builder{
            this.respect=respect
            return this
        }

        fun lastVisit(date:Date):Builder{
            this.lastVisit=date
            return this
        }

        fun isOnline(isOnline: Boolean):Builder{
            this.isOnline = isOnline
            return this
        }

        fun build():User{
            return User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
        }
    }
}