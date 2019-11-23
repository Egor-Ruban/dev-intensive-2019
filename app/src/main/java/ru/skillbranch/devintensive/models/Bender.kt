package ru.skillbranch.devintensive.models

class Bender(
    var status: Status = Status.NORMAL,
    var question: Question = Question.NAME
) {

    fun askQuestion():String = when(question){
                Question.NAME ->Question.NAME.question
                Question.PROFESSION ->Question.PROFESSION.question
                Question.MATERIAL ->Question.MATERIAL.question
                Question.BDAY ->Question.BDAY.question
                Question.SERIAL ->Question.SERIAL.question
                Question.IDLE ->Question.IDLE.question
    }

    fun listenAnswer(answer:String):Pair<String,Triple<Int,Int,Int>>{
        return if(question.answers.contains(answer)){
            question = question.nextQuestion()
            "Отлично, это правильный ответ\n${question.question}" to status.color
        } else {
            status = status.nextStatus()
            "Это не правильный ответ!\n${question.question}" to status.color
        }
    }

    enum class Status(
        val color:Triple<Int,Int,Int>
    ){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,255,0));

        fun nextStatus():Status{
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal+1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(
        val question: String,
        val answers:MutableList<String>
    ){
        NAME("Как меня зовут?", mutableListOf("бендер", "bender")){
            override fun nextQuestion(): Question = NAME
        },
        PROFESSION("Назови свою профессию", mutableListOf("сгибальщик","bender")){
            override fun nextQuestion(): Question = PROFESSION
        },
        MATERIAL("Из чего я сделан?", mutableListOf("металл","дерево","metal","iron","wood")){
            override fun nextQuestion(): Question = MATERIAL
        },
        BDAY("Когда меня создали?", mutableListOf("2993")){
            override fun nextQuestion(): Question = BDAY
        },
        SERIAL("Мой серийный номер?", mutableListOf("2716057")){
            override fun nextQuestion(): Question = SERIAL
        },
        IDLE("На этом все, вопросов больше нет", mutableListOf("")){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion():Question
    }
}