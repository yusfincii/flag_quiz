package com.example.flagquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flagquiz.databinding.ActivityEntryBinding

class EntryActivity : AppCompatActivity() {
    private lateinit var entry : ActivityEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entry = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(entry.root)
        // database copy process
        copyDatabase()

        // transaction to quiz screen
        entry.buttonStart.setOnClickListener {
            startActivity(Intent(this@EntryActivity, QuizActivity::class.java))
        }
    }

    private fun copyDatabase(){
        // object from database copy helper class
        val dbCopyHelper = DatabaseCopyHelper(this)
        try {
            dbCopyHelper.createDataBase()
            dbCopyHelper.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}