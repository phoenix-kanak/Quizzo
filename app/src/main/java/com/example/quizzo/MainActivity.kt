package com.example.quizzo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun PlayNow(view: View) {
        val i= Intent(this,Level::class.java)
        startActivity(i)
    }

    fun About(view: View) {
        val i= Intent(this,AboutActivity::class.java)
        startActivity(i)
    }
}