package com.example.calculator2

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(Color.parseColor("#FF018786"))
        )

        var result: String? = intent.getStringExtra("result")
        if (result.isNullOrEmpty()) {
            result = "No response".toString()
        }
        binding.result.text = result

        binding.backToCalculator.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}