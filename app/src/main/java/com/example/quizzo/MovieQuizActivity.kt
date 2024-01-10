package com.example.quizzo

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityQuizMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list= ArrayList<QuestionModel>()

        var count:Int=1
        var buttonClicked:Boolean=false
        val response:ArrayList<Int> = ArrayList()
        val answer:ArrayList<Int> = ArrayList()
        answer.add(2)
        answer.add(1)
        answer.add(2)
        answer.add(3)
        answer.add(1)



        list.add(QuestionModel(R.drawable.harry_potter,
            "Which actress portrayed Hermione Granger in the Harry Potter film series?" ,
            "Emma Stone" ,
            "Emma Watson" ,
            "Emma Roberts"))
        list.add(QuestionModel(R.drawable.money_heist ,
            "In famous series \"Money Heist\",on what basis the heist crew adopted code names?" ,
            "Cities" ,
            "Actors" ,
            "Planets"))
        list.add(QuestionModel(R.drawable.mocambo ,
            "The famous dialogue: \"Mogambo khush hua\" is a line from which Bollywood film?" ,
            "Sholay" ,
            "Mr. India" ,
            "Don"))
        list.add(QuestionModel(R.drawable.inception ,
            "In the movie \"Inception,\" what is the term used for the device that allows entry into dreams?" ,
            "Dreamcatcher" ,
            "Somnacin" ,
            "PASIV" ))
        list.add(QuestionModel(R.drawable.mirza_pur,
            "Complete the dialogue from \"Mirzapur\": \"Shuru Majboori me kiye the _____.\"",
            "Par ab maja aa rha hai" ,
            "Aur ab kuch nhi ho pa rha hai" ,
            "Ab majboori zarurat ban gayi hai"))

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



        binding.next.setOnClickListener{
            if(count==5){
                var i:Int=0
                var number:Int=0
                while(i<5){
                    if(response[i] == answer[i]){
                        ++number
                    }
                    i++
                }
                startActivity(Intent(this , ResultActivity::class.java))
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

                val builder = AlertDialog.Builder(this@MovieQuizActivity)
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