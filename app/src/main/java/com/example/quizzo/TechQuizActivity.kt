package com.example.quizzo

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizzo.databinding.ActivityTechQuizBinding
import com.example.quizzo.models.QuestionModel

class TechQuizActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var list: ArrayList<QuestionModel>
    private lateinit var binding: ActivityTechQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTechQuizBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var count:Int=1
        var buttonClicked:Boolean=false
        val response:ArrayList<Int> = ArrayList()
        val answer:ArrayList<Int> = ArrayList()

        list= ArrayList<QuestionModel>()

        list.add(
            QuestionModel(R.drawable.swift_logo,
                "Guess the programming language with its logo.",
                "Java",
                "Ruby",
                "Swift"
            )
        )
        list.add(
            QuestionModel(R.drawable.proramming_language,
            "Which of the following is not an object oriented programming language?",
            "Python",
                "C",
                "C++"
            )
        )
        list.add(
            QuestionModel(R.drawable.kotlin_mascot,
                "Which programming language to this mascot belongs?",
                "Java",
                "GoLang",
                "Kotlin"
            )
        )

        list.add(
            QuestionModel(R.drawable.google_maps,
                "Google Maps is an application of which data structure?",
                "Graphs",
                "Arrays",
                "Queues"
            )
        )
        list.add(
            QuestionModel(R.drawable.gaming_language ,
                "Which of the following language is used as scripting language for gaming apps?",
                "C#",
                "Javascript",
                "C"
            )
        )

        binding.imageView.setImageDrawable(ContextCompat.getDrawable(this, list[0].image))
        binding.ques.text = list[0].ques
        binding.option1.text = list[0].option1
        binding.option2.text = list[0].option2
        binding.option3.text = list[0].option3

        binding.option1.setOnClickListener {
            response.add(1)
            buttonClicked=true
        }
        binding.option2.setOnClickListener {
            response.add(2)
            buttonClicked=true
        }
        binding.option3.setOnClickListener {
            response.add(3)
            buttonClicked=true
        }
        answer.add(3)
        answer.add(2)
        answer.add(3)
        answer.add(1)
        answer.add(1)

        binding.next.setOnClickListener{
            if(count==5){
                var i:Int=0
                var score:Int=0
                while(i<5){
                    if(response[i] == answer[i]){
                        score+=10
                    }
                    i++
                }
                val intent=Intent(this,ResultActivity::class.java)
                intent.putExtra("Score",score )
                startActivity(intent)
            }
            if(!buttonClicked){
                Toast.makeText(this , "Please select an option" , Toast.LENGTH_SHORT).show()
            }else {
                nextQues(count)
                ++count
                countDownTimer.start()
                buttonClicked=false
            }
        }

        progressBar = binding.timer

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

                //progressBar.progress = 0

                val builder = AlertDialog.Builder(this@TechQuizActivity)
                builder.setTitle("Time Over!")
                builder.setMessage("Oops time finished")
                builder.setNegativeButton("See the score") { dialog, which ->
                    dialog.dismiss()
                    finish()
                }
                val dialog = builder.create()
                dialog.show()

            }
        }

        // Start the timer
        countDownTimer.start()

    }
    private fun nextQues(count: Int) {
        binding.imageView.setImageDrawable(ContextCompat.getDrawable(this, list[count].image))
        binding.ques.text = list[count].ques
        binding.option1.text = list[count].option1
        binding.option2.text = list[count].option2
        binding.option3.text = list[count].option3

    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}