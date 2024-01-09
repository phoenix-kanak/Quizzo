package com.example.quizzo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.quizzo.databinding.ActivityQuizBinding
import com.example.quizzo.models.QuestionModel
import org.w3c.dom.Text
import java.util.ArrayList

class QuizActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var list: ArrayList<QuestionModel>
    private lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list= ArrayList<QuestionModel>()

        list.add(QuestionModel(R.drawable.harry_potter,
            "Which actress portrayed Hermione Granger in the Harry Potter film series?" ,
            "Emma Stone" ,
            "Emma Watson" ,
            "Emma Roberts"))
        list.add(QuestionModel(R.drawable.money_heist ,
            "In famous series \"Money Heist\" (La Casa de Papel), the members of the heist crew adopt code names to conceal their true identities. What was its basis?" ,
            "Actors" ,
            "Cities" ,
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

//        val image=findViewById<ImageView>(R.id.imageView)
//        val ques=findViewById<TextView>(R.id.ques)
//        val option1=findViewById<Button>(R.id.option1)
//        val option2=findViewById<Button>(R.id.option2)
//        val option3=findViewById<Button>(R.id.option3)
        binding.imageView.setImageDrawable(ContextCompat.getDrawable(this, list[0].image))
        binding.ques.setText( list[0].ques)
        binding.option1.setText (list[0].option1)
        binding.option2.setText (list[0].option2)
        binding.option3.setText ( list[0].option3)



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

                val builder = AlertDialog.Builder(this@QuizActivity)
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

    override fun onDestroy() {
        super.onDestroy()
        // Cancel the timer to avoid memory leaks
        countDownTimer.cancel()
    }
}