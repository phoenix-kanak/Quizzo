package com.example.quizzo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzo.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val score=intent.getStringExtra("Score").toString()
        binding.score.text=score
    }
}