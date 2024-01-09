package com.example.quizzo

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog

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

    fun StartQuiz(view: View) {
        val intent=Intent(this,QuizActivity::class.java)
        startActivity(intent)
        //finish()
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
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
              //  super.onBackPressed()
            })
            .show()
       // super.onBackPressed()
    }
}