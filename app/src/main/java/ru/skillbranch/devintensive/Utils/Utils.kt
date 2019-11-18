package ru.skillbranch.devintensive.Utils

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

    fun toInitials(firstName:String?,secondName:String?):String?{
        var tempFirstName = firstName
        var tempSecondName = secondName
        var firstLetter:String?
        var secondLetter:String?
        if(firstName===null){ firstLetter = null}
        else {
            tempFirstName = firstName!!.replace(" ", "")
            if(tempFirstName==""){
                firstLetter = null
            } else {
                firstLetter = firstName[0].toString().capitalize()
            }
        }
        if(secondName===null){ secondLetter = null}
        else {
            tempSecondName = secondName!!.replace(" ", "")
            if(tempSecondName==""){
                secondLetter = null
            } else {
                secondLetter = secondName[0].toString().capitalize()
            }
        }

        if     (firstLetter==null&&secondLetter==null) return null
        else if(firstLetter==null&&secondLetter!=null) return "$secondLetter"
        else if(firstLetter!=null&&secondLetter==null) return "$firstLetter"
        return "$firstLetter$secondLetter"

    }
}