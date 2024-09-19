package com.example.iamahistorian

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var tvScore: TextView
    private lateinit var tvResult: TextView
    private lateinit var btnExit: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvScore = findViewById(R.id.tvScore)
        tvResult = findViewById(R.id.tvResult)
        btnExit = findViewById(R.id.buttonExit)

        val score = intent.getIntExtra("score", 0)
        tvScore.text = "Баллы: $score"

        val result = when {
            score == 500 -> "Отличный уровень знаний"
            score >= 400 -> "Хороший уровень знаний"
            score >= 300 -> "Удовлетворительный уровень знаний"
            score >= 200 -> "Неудовлетворительный уровень знаний"
            else -> "Плохой уровень знаний"
        }

        tvResult.text = result

        btnExit.setOnClickListener { finishAffinity() }
    }
}