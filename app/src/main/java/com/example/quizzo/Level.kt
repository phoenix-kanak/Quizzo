package com.example.quizzo

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
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
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->

            })
            .show()
    }
}