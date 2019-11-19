package ru.skillbranch.devintensive.utils

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
        var firstName = firstName
        var secondName = secondName
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
}