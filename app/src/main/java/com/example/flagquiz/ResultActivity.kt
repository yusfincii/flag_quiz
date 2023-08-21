package com.example.flagquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flagquiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var result : ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        result = ActivityResultBinding.inflate(layoutInflater)
        setContentView(result.root)

        // to get informations from intent object
        val trueCount = intent.getIntExtra("trueCount", 0)
        val falseCount = intent.getIntExtra("falseCount", 0)

        // to placing results
        result.trueCountResult.text = "True : $trueCount"
        result.falseCountResult.text = "False : $falseCount"

        result.buttonPlayAgain.setOnClickListener {
            // when play again button pressed it navigate quiz screen
            // and game starts again
            startActivity(Intent(this@ResultActivity, QuizActivity::class.java))
            finish()
        }
    }
}