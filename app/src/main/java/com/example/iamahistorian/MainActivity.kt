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
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var btnStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        btnStart = findViewById(R.id.btnStart)

        btnStart.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("score", 0)
            intent.putExtra("questionIndex", 0)
            startActivity(intent)
        }
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