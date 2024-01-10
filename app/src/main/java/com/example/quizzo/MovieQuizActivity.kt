package com.example.quizzo

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
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
    val response: ArrayList<Int> = ArrayList()
    val answer: ArrayList<Int> = ArrayList()
    var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityQuizMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list = ArrayList<QuestionModel>()


        var count: Int = 1
        var buttonClicked: Boolean = false

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

        binding.imageView.setImageDrawable(ContextCompat.getDrawable(this, list[0].image))
        binding.ques.text = list[0].ques
        binding.option1.text = list[0].option1
        binding.option2.text = list[0].option2
        binding.option3.text = list[0].option3

        binding.option1.setOnClickListener {
            response.add(1)
            getScore(count)
            buttonClicked = true
        }
        binding.option2.setOnClickListener {
            response.add(2)
            getScore(count)
            buttonClicked = true
        }
        binding.option3.setOnClickListener {
            response.add(3)
            getScore(count)
            buttonClicked = true
        }



        binding.next.setOnClickListener {
            if (count == 5) {
                score = getScore(count)

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("Score", score.toString())
                //  Log.e("Score123" , "$score")
                startActivity(intent)
                finish()
            }
            if (!buttonClicked) {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            } else {
                nextQues(count)
                ++count
                countDownTimer.start()
                buttonClicked = false
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

                val builder = AlertDialog.Builder(this@MovieQuizActivity)
                builder.setTitle("Time Over!")
                builder.setMessage("Oops time finished")
                builder.setNegativeButton("See the score") { dialog, which ->
                    dialog.dismiss()
                    val intent = Intent(this@MovieQuizActivity, ResultActivity::class.java)
                    intent.putExtra("Score", score.toString())
                    //  Log.e("Score123" , "$score")
                    startActivity(intent)
                    finish()
                }

                val dialog = builder.create()
                dialog.show()
                val mainLayout =
                    findViewById<View>(R.id.activity_movie) // Replace with your actual layout ID
                mainLayout.setOnClickListener {
                    startActivity(Intent(this@MovieQuizActivity, ResultActivity::class.java))
                    getScore(count)
                    val intent = Intent(this@MovieQuizActivity, ResultActivity::class.java)
                    intent.putExtra("Score", score.toString())
                    //  Log.e("Score123" , "$score")
                    startActivity(intent)
                    finish()
                }
            }
        }

        // Start the timer
        countDownTimer.start()
    }

    private fun getScore(count: Int): Int {
        var i = 0
        while (i < count) {
            if (response[i] == answer[i]) {
                score += 10
            }
            i++
        }
        return score
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

    override fun onBackPressed() {
        AlertDialog.Builder(this)

            .setTitle("Exit")

            .setMessage("Are you sure?")

            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                dialog, which ->
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent. CATEGORY_HOME )
                intent. flags  = Intent. FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } )

            .setNegativeButton(
                "No",
                DialogInterface.OnClickListener {  dialog, which ->

                }).show()
    }
}