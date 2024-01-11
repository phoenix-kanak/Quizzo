package com.example.quizzo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Level : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
    }

    fun Back(view: View) {
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun StartMovieQuiz(view: View) {
        val intent=Intent(this,MovieQuizActivity::class.java)
        startActivity(intent)
        //finish()
    }
    fun StartTechQuiz(view: View){
        val intent=Intent(this,TechQuizActivity::class.java)
        startActivity(intent)
    }
}