package com.example.calculator2

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator2.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(Color.parseColor("#FF018786"))
        )

        binding.getResult.setOnClickListener {
            if (inputIsNotEmpty()) {
                val result =
                    when (resources.getResourceEntryName(binding.operations.checkedRadioButtonId)) {
                        "plus" -> add()
                        "minus" -> subtract()
                        "multiply" -> multiply()
                        "divide" -> divide()
                        else -> "0"
                    }
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("result", result)
                startActivity(intent)
            }
        }
    }

    private fun inputIsNotEmpty(): Boolean {
        var isReady = true;
        if (binding.input1.text.toString().trim().isEmpty()) {
            binding.input1.error = "Required"
            binding.input1.requestFocus()
            isReady = false;
        }
        if (binding.input2.text.toString().trim().isEmpty()) {
            binding.input2.error = "Required"
            binding.input2.requestFocus()
            isReady = false;
        }
        return isReady;
    }

    fun add(): String {
        val inputData1 = binding.input1.text.toString().trim().toBigDecimal()
        val inputData2 = binding.input2.text.toString().trim().toBigDecimal()
        return inputData1.add(inputData2).toString()
    }

    fun subtract(): String {
        val inputData1 = binding.input1.text.toString().trim().toBigDecimal()
        val inputData2 = binding.input2.text.toString().trim().toBigDecimal()
        return inputData1.subtract(inputData2).toString()
    }

    fun multiply(): String {
        val inputData1 = binding.input1.text.toString().trim().toBigDecimal()
        val inputData2 = binding.input2.text.toString().trim().toBigDecimal()
        return inputData1.multiply(inputData2).toString()
    }

    fun divide(): String {
        val inputData1 = binding.input1.text.toString().trim().toBigDecimal()
        val inputData2 = binding.input2.text.toString().trim().toBigDecimal()
        return if (inputData2.compareTo(BigDecimal.ZERO) == 0) {
            "you can't divide to 0"
        } else {
            inputData1.divide(inputData2, 2, RoundingMode.HALF_UP).toString()
        }
    }

}
