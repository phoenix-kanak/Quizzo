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
import com.example.quizzo.databinding.ActivityQuizMovieBinding
import com.example.quizzo.models.QuestionModel


class MovieQuizActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var list: ArrayList<QuestionModel>
    private lateinit var binding: ActivityQuizMovieBinding
    private var selectedOption: Button? = null
    val response: ArrayList<Int> = ArrayList()
    val answer: ArrayList<Int> = ArrayList()
    var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityQuizMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list = ArrayList<QuestionModel>()


        var count: Int = 0
        // var buttonClicked: Boolean = false

        answer.add(2)
        answer.add(1)
        answer.add(2)
        answer.add(3)
        answer.add(1)

        list.add(
            QuestionModel(
                R.drawable.harry_potter,
                "Which actress portrayed Hermione Granger in the Harry Potter film series?",
                "Emma Stone",
                "Emma Watson",
                "Emma Roberts"
            )
        )
        list.add(
            QuestionModel(
                R.drawable.money_heist,
                "In famous series \"Money Heist\",on what basis the heist crew adopted code names?",
                "Cities",
                "Actors",
                "Planets"
            )
        )
        list.add(
            QuestionModel(
                R.drawable.mocambo,
                "The famous dialogue: \"Mogambo khush hua\" is a line from which Bollywood film?",
                "Sholay",
                "Mr. India",
                "Don"
            )
        )
        list.add(
            QuestionModel(
                R.drawable.inception,
                "In the movie \"Inception,\" what is the term used for the device that allows entry into dreams?",
                "Dreamcatcher",
                "Somnacin",
                "PASIV"
            )
        )
        list.add(
            QuestionModel(
                R.drawable.mirza_pur,
                "Complete the dialogue from \"Mirzapur\": \"Shuru Majboori me kiye the _____.\"",
                "Par ab maja aa rha hai",
                "Aur ab kuch nhi ho pa rha hai",
                "Ab majboori zarurat ban gayi hai"
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
            }else {
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

        val totalTimeInMillis: Long = 10000   // Set the total time for the timer in milliseconds (e.g., 10 seconds)
        val intervalInMillis: Long = 100      // Set the interval for updating the progress bar in milliseconds (e.g., 100 milliseconds)


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
        if(count==5){
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("Score", score.toString())
            Log.e("score123", score.toString())
            startActivity(intent)
            finish()
        }

        countDownTimer.start()
        selectedOption?.setBackgroundResource(R.drawable.trans_button)
        selectedOption=null
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
        AlertDialog.Builder(this)
            .setTitle("Exit")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(this@MovieQuizActivity,Level::class.java)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
            }).show()
    }
}