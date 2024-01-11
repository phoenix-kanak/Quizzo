package com.example.quizzo

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
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
    private var selectedOption: Button? = null
    private val response: ArrayList<Int> = ArrayList()
    private val answer: ArrayList<Int> = ArrayList()
    private var score: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTechQuizBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var count: Int = 0
     //   var buttonClicked: Boolean = false
        list = ArrayList<QuestionModel>()

        answer.add(3)
        answer.add(2)
        answer.add(3)
        answer.add(1)
        answer.add(1)

        list.add(
            QuestionModel(
                R.drawable.swift_logo,
                "Guess the programming language with its logo.",
                "Java",
                "Ruby",
                "Swift"
            )
        )
        list.add(
            QuestionModel(
                R.drawable.proramming_language,
                "Which of the following is not an object oriented programming language?",
                "Python",
                "C",
                "C++"
            )
        )
        list.add(
            QuestionModel(
                R.drawable.kotlin_mascot,
                "Which programming language to this mascot belongs?",
                "Java",
                "GoLang",
                "Kotlin"
            )
        )

        list.add(
            QuestionModel(
                R.drawable.google_maps,
                "Google Maps is an application of which data structure?",
                "Graphs",
                "Arrays",
                "Queues"
            )
        )
        list.add(
            QuestionModel(
                R.drawable.gaming_language,
                "Which of the following language is used as scripting language for gaming apps?",
                "C#",
                "Javascript",
                "C"
            )
        )

        binding.imageView.setImageDrawable(ContextCompat.getDrawable(this, list[count].image))
        binding.ques.text = list[count].ques
        binding.option1.text = list[count].option1
        binding.option2.text = list[count].option2
        binding.option3.text = list[count].option3

        binding.option1.setOnClickListener {
            selectOption(binding.option1)
//            response.add(1)
//            buttonClicked = true
        }
        binding.option2.setOnClickListener {
            selectOption(binding.option2)
//            response.add(2)
//            buttonClicked = true
        }
        binding.option3.setOnClickListener {
            selectOption(binding.option3)
//            response.add(3)
//            buttonClicked = true
        }

        binding.next.setOnClickListener {
            if (selectedOption?.id == R.id.option1) {
                response.add(1)
            }
            if (selectedOption?.id == R.id.option2) {
                response.add(2)
            }
            if (selectedOption?.id == R.id.option3) {
                response.add(3)
            }
            if (selectedOption == null) {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            } else {
                score = getScore(count)
                if (count == 4) {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("Score", score.toString())
                    Log.e("score123", score.toString())
                    startActivity(intent)
                    finish()
                } else {
                    count++
                    nextQues(count)
                    countDownTimer.start()
                    //  buttonClicked = false
                }
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
                response.add(0)
                getScore(count)
                nextQues(++count)
            }
        }

        // Start the timer
        countDownTimer.start()


    }

    private fun selectOption(optionButton: Button) {
        // Reset the background color for the previously selected option
        //  selectedOption=null
        selectedOption?.setBackgroundResource(R.drawable.trans_button)
        // Set the background color for the newly selected option
        optionButton.setBackgroundResource(R.drawable.solid_button)
        selectedOption = optionButton
    }

    private fun getScore(count: Int): Int {
        if (response[count] == answer[count]) {
            score += 10
        }
        return score
    }

    private fun nextQues(count: Int) {
        if (count == 5) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("Score", score.toString())
            Log.e("score123", score.toString())
            startActivity(intent)
            finish()
        }

        countDownTimer.start()
        selectedOption?.setBackgroundResource(R.drawable.trans_button)
        selectedOption = null
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

    override fun onBackPressed() {
        AlertDialog.Builder(this).setTitle("Exit").setMessage("Are you sure?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(this@TechQuizActivity, Level::class.java)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }).setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
            }).show()
    }
}