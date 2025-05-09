package com.example.geoquiz

import androidx.lifecycle.ViewModel

@Suppress("unused")
private const val TAG: String = "QuizViewModel"

class QuizViewModel : ViewModel() {
    var currentIndex = 0
    var isCheat = false
    var numberOfCorrectAnswers: Int = 0
    var numberOfCheatsLeft = 3
    val questionBank = listOf(
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true),
        Question(R.string.question_mexico,false),
        Question(R.string.question_zealand,true),
        Question(R.string.question_china,true),
        Question(R.string.question_paris,true),
        Question(R.string.question_africa_land,true)
    )

    val currentQuestionAnswer: Boolean
        get() {
           return questionBank[currentIndex].Answer
        }
    val currenQuestionText: Int
        get() {
            return questionBank[currentIndex].TextResId
        }


    fun useCheat(){
        numberOfCheatsLeft -= 1
        if (numberOfCheatsLeft <  0)
        {
            isCheat = true
        }
    }
    fun moveToNext (){
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}