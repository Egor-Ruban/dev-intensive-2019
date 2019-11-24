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
        return when(question){
            Question.IDLE -> question.question to status.color
            else -> "${checkAnswer(answer)}\n${question.question}" to status.color
        }
    }

    private fun checkAnswer(answer: String): String {
        return if (question.answers.contains(answer)) {
            question = question.nextQuestion()
            "Отлично - ты справился"
        }
        else {
            if (status == Status.CRITICAL){
                resetStates()
                "Это неправильный ответ. Давай все по новой"
            }
            else{
                status = status.nextStatus()
                "Это неправильный ответ"
            }
        }
    }

    private fun resetStates() {
        status = Status.NORMAL
        question = Question.NAME
    }

    enum class Status(
        val color:Triple<Int,Int,Int>
    ){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,0,0));

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
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", mutableListOf("сгибальщик","bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", mutableListOf("металл","дерево","metal","iron","wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", mutableListOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", mutableListOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", mutableListOf("")){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion():Question
    }
}