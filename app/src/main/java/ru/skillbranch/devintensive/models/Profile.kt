package ru.skillbranch.devintensive.models


data class Profile (
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
) {
    val nickName = nickName()
    val rank: String = "Junior Android Developer"

    fun toMap(): Map<String, Any> = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect
    )

    fun translate(str : String):String{
        var fullName = str
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

        return fullName
    }

    fun nickName():String{
        val fname = translate(firstName)
        val sname = translate(lastName)
        val nick = if(fname == "" && sname =="") ""
         else if(fname == "") sname
         else if(sname == "") fname
        else fname + "_" + sname
        return nick
    }
}