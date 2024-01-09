package com.example.quizzo

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import java.util.ArrayList

class QuizActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var countDownTimer: CountDownTimer
//    private lateinit var ques: ArrayList<QuestionModel>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        progressBar = findViewById(R.id.timer)

        // Set the total time for the timer in milliseconds (e.g., 10 seconds)
        val totalTimeInMillis: Long = 10000

        // Set the interval for updating the progress bar in milliseconds (e.g., 100 milliseconds)
        val intervalInMillis: Long = 100

        countDownTimer = object : CountDownTimer(totalTimeInMillis, intervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (millisUntilFinished / intervalInMillis).toInt()
                progressBar.progress = progress
            }

            override fun onFinish() {
                // Do something when the timer finishes
                progressBar.progress = 0
            }
        }

        // Start the timer
        countDownTimer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancel the timer to avoid memory leaks
        countDownTimer.cancel()
    }
}