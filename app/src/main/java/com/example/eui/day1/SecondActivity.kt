package com.example.eui.day1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eui.R

class SecondActivity : AppCompatActivity() {

    private lateinit var myData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myData = findViewById(R.id.textView)

        val email = intent.getStringExtra("EMAIL") // ahmdaliielsayed@gmail.com
        val mobile = intent.getStringExtra("MOBILE") // 01063208399

        myData.text = "Email: $email \nMobile: $mobile"
    }
}