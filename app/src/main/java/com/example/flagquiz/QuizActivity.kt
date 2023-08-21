package com.example.flagquiz

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.flagquiz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var quiz : ActivityQuizBinding // view binding

    private lateinit var flags : ArrayList<Flag> // arraylist which holds flag objects
    private lateinit var wrongChoises : ArrayList<String> // wrong 3 option
    private lateinit var rightChoise : String // right option

    private lateinit var allOptions : ArrayList<String>
    private lateinit var buttons : ArrayList<Button>
    private lateinit var dbHelper : DatabaseHelper

    private var questionCounter = 1
    private var trueCounter = 0
    private var falseCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quiz = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(quiz.root)

        dbHelper = DatabaseHelper(this)

        // creating flag arraylist
        flags = Flagdao().getRandom10Flag(dbHelper)

        // initializing buttons arraylist and adding operations
        buttons = ArrayList()
        buttons.add(quiz.button1)
        buttons.add(quiz.button2)
        buttons.add(quiz.button3)
        buttons.add(quiz.button4)

        // loop works for each flag
        flags.forEach {
            // right choise flag name
            rightChoise = it.flagName
            // 3 wrong flag name list except right choise
            wrongChoises = Flagdao().getRandomWrongOptions(dbHelper, rightChoise)
            // all options added to list
            wrongChoises.forEach {itX ->
                allOptions.add(itX)
            }
            allOptions.add(rightChoise)
            // mixing all options
            allOptions.shuffle()
            // button text's specifying
            for((index, currentButton) in buttons.withIndex()){
                currentButton.text = allOptions[index]
            }
            // question number text increase
            quiz.textViewQuestionNumber.text = "Question : $questionCounter"
            questionCounter++
            // set current flag
            quiz.imageViewFlag.setImageResource(resources.getIdentifier(it.flagImage, "drawable", packageName))

            buttons.forEach{itZ ->
                // if button which has true flag name clicked
                if(itZ.text.equals(rightChoise)){
                    itZ.setOnClickListener{
                        // increase true count
                        trueCounter ++
                    }
                }
                else{
                    // increase false count
                    falseCounter ++
                }
            }
        }
        // transition to result screen
        val intent = Intent(this@QuizActivity, ResultActivity::class.java)
        // necessary informations adding to intent object
        intent.putExtra("trueCount", trueCounter)
        intent.putExtra("falseCount", falseCounter)
        // to when pressed back button it navigate to entry screen
        finish()
    }
}