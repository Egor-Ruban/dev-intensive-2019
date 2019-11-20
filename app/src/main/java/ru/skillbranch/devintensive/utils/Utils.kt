package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {

    fun parseFullName(fullName:String?):Pair<String?,String?>{

        when(fullName?.replace(" ","")){
            null,"" -> return Pair(null, null)
        }

        if(fullName!!.contains(" ")){
            var (firstName, lastName) = fullName.split(" ")
            firstName = firstName.replace(" ","")
            lastName = lastName.replace(" ","")

            if(lastName == "")
                return(firstName to null)
            else return (firstName to lastName)
        }
        return(fullName to null)
    }

    fun toInitials(firstName_:String?,secondName_:String?):String?{
        var firstName = firstName_
        var secondName = secondName_
        if(firstName==null||secondName==null){
            if(firstName==null && secondName == null){
                return null
            }
            else if(firstName!=null && secondName == null){
                firstName = firstName.replace(" ","")
                if(firstName=="") return null
                return firstName[0].toUpperCase().toString()
            }
            else if(firstName==null && secondName != null){
                secondName = secondName.replace(" ","")
                if(secondName=="") return null
                return secondName[0].toUpperCase().toString()
            }
        }
        else{
            firstName = firstName.replace(" ","")
            secondName = secondName.replace(" ","")
            if(firstName=="" && secondName == ""){
                return null
            }
            else if(firstName!="" && secondName == ""){
                return firstName[0].toUpperCase().toString()
            }
            else if(firstName=="" && secondName != ""){
                return secondName[0].toUpperCase().toString()
            }
            else if(firstName!="" && secondName != ""){
                return firstName[0].toUpperCase().toString() + secondName[0].toUpperCase().toString()
            }

        }
        return null
    }


    fun transliteration(payload:String?, divider:String = " "):String?{
        var fullName = payload
        if(fullName == null) fullName = ""

        fullName = fullName.replace("а", "a")
        fullName = fullName.replace("б", "b")
        fullName = fullName.replace("в", "v")
        fullName = fullName.replace("г", "g")
        fullName = fullName.replace("д", "d")
        fullName = fullName.replace("е", "e")
        fullName = fullName.replace("ё", "e")
        fullName = fullName.replace("ж", "zh")
        fullName = fullName.replace("з", "z")
        fullName = fullName.replace("и", "i")
        fullName = fullName.replace("й", "i")
        fullName = fullName.replace("к", "k")
        fullName = fullName.replace("л", "l")
        fullName = fullName.replace("м", "m")
        fullName = fullName.replace("н", "n")
        fullName = fullName.replace("о", "o")
        fullName = fullName.replace("п", "p")
        fullName = fullName.replace("р", "r")
        fullName = fullName.replace("с", "s")
        fullName = fullName.replace("т", "t")
        fullName = fullName.replace("у", "u")
        fullName = fullName.replace("ф", "f")
        fullName = fullName.replace("х", "h")
        fullName = fullName.replace("ц", "c")
        fullName = fullName.replace("ч", "ch")
        fullName = fullName.replace("ш", "sh")
        fullName = fullName.replace("щ", "sh'")
        fullName = fullName.replace("ъ", "")
        fullName = fullName.replace("ы", "i")
        fullName = fullName.replace("ь", "")
        fullName = fullName.replace("э", "e")
        fullName = fullName.replace("ю", "yu")
        fullName = fullName.replace("я", "ya")

        fullName = fullName.replace("А", "A")
        fullName = fullName.replace("б".toUpperCase(), "B")
        fullName = fullName.replace("в".toUpperCase(), "V")
        fullName = fullName.replace("г".toUpperCase(), "G")
        fullName = fullName.replace("д".toUpperCase(), "D")
        fullName = fullName.replace("е".toUpperCase(), "E")
        fullName = fullName.replace("ё".toUpperCase(), "E")
        fullName = fullName.replace("ж".toUpperCase(), "Zh")
        fullName = fullName.replace("з".toUpperCase(), "Z")
        fullName = fullName.replace("и".toUpperCase(), "I")
        fullName = fullName.replace("й".toUpperCase(), "I")
        fullName = fullName.replace("к".toUpperCase(), "K")
        fullName = fullName.replace("л".toUpperCase(), "L")
        fullName = fullName.replace("м".toUpperCase(), "M")
        fullName = fullName.replace("н".toUpperCase(), "N")
        fullName = fullName.replace("о".toUpperCase(), "O")
        fullName = fullName.replace("п".toUpperCase(), "P")
        fullName = fullName.replace("р".toUpperCase(), "R")
        fullName = fullName.replace("с".toUpperCase(), "S")
        fullName = fullName.replace("т".toUpperCase(), "T")
        fullName = fullName.replace("у".toUpperCase(), "U")
        fullName = fullName.replace("ф".toUpperCase(), "F")
        fullName = fullName.replace("х".toUpperCase(), "H")
        fullName = fullName.replace("ц".toUpperCase(), "C")
        fullName = fullName.replace("ч".toUpperCase(), "Ch")
        fullName = fullName.replace("ш".toUpperCase(), "Sh")
        fullName = fullName.replace("щ".toUpperCase(), "Sh'")
        fullName = fullName.replace("ъ".toUpperCase(), "")
        fullName = fullName.replace("ы".toUpperCase(), "I")
        fullName = fullName.replace("ь".toUpperCase(), "")
        fullName = fullName.replace("э".toUpperCase(), "E")
        fullName = fullName.replace("ю".toUpperCase(), "Yu")
        fullName = fullName.replace("я".toUpperCase(), "Ya")

        fullName = fullName.replace(" ",divider)

        return fullName
    }
}