package com.example.iamahistorian

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {

    private val questions = listOf(
        Question(
            "Какое событие произошло в 988 году в Киевской Руси?",
            listOf("Крещение Руси", "Основание Москвы", "Первый съезд князей"),
            0
        ),
        Question(
            "Какое сражение стало решающим в Куликовской битве?",
            listOf("Сражение на Угре", "Битва при Молодях", "Мамаево побоище"),
            2
        ),
        Question(
            "Кто был первым российским императором?",
            listOf("Петр I", "Иван IV", "Екатерина II"),
            0
        ),
        Question("Какой год считается годом основания Москвы?", listOf("1147", "1157", "1137"), 0),
        Question("В каком году состоялось Бородинское сражение?", listOf("1810", "1812", "1814"), 1)
    )

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var tvQuestion: TextView
    private lateinit var btnNext: Button
    private lateinit var rbAnswer1: RadioButton
    private lateinit var rbAnswer2: RadioButton
    private lateinit var rbAnswer3: RadioButton

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        tvQuestion = findViewById(R.id.tvQuestion)
        btnNext = findViewById(R.id.btnNext)
        rbAnswer1 = findViewById(R.id.rbAnswer1)
        rbAnswer2 = findViewById(R.id.rbAnswer2)
        rbAnswer3 = findViewById(R.id.rbAnswer3)

        currentQuestionIndex = intent.getIntExtra("questionIndex", 0)
        score = intent.getIntExtra("score", 0)

        loadQuestion()

        btnNext.setOnClickListener {
            val selectedAnswer = when {
                rbAnswer1.isChecked -> 0
                rbAnswer2.isChecked -> 1
                rbAnswer3.isChecked -> 2
                else -> -1
            }

            if (selectedAnswer == questions[currentQuestionIndex].correctAnswer) {
                score += 100
            }

            if (currentQuestionIndex < questions.size - 1) {
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("questionIndex", currentQuestionIndex + 1)
                intent.putExtra("score", score)
                startActivity(intent)
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
            }
        }
    }

    private fun loadQuestion() {
        val question = questions[currentQuestionIndex]
        tvQuestion.text = question.text
        rbAnswer1.text = question.answers[0]
        rbAnswer2.text = question.answers[1]
        rbAnswer3.text = question.answers[2]
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.general_menu, menu)
        // Изменение цвета текста меню программно
        menu?.let {
            for (i in 0 until it.size()) {
                val menuItem = it.getItem(i)
                val spanString = SpannableString(menuItem.title.toString())
                spanString.setSpan(
                    ForegroundColorSpan(Color.BLACK), // Замените на нужный вам цвет
                    0,
                    spanString.length,
                    0
                )
                menuItem.title = spanString
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_exit) {
            finishAffinity()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

data class Question(val text: String, val answers: List<String>, val correctAnswer: Int)