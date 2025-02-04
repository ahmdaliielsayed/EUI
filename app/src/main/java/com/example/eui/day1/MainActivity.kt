package com.example.eui.day1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eui.R

class MainActivity : AppCompatActivity() {

    // (X) main method
    // lifecycle --> will show --> shown --> will be hidden --> hidden

    var counter = 0

    lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // inflation
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // initialization - inflation - not visible
        Log.d("MainActivity --> lifecycle: ", "onCreate()")

        if (savedInstanceState == null) {
            Log.d("MainActivity --> lifecycle: ", "first enter")
        } else {
            Log.d("MainActivity --> lifecycle: ", "second time")
            counter = savedInstanceState.getInt("COUNTER")
            counter++
            Log.d("MainActivity --> lifecycle: ", "second time & counter = $counter")
        }

        val textChange = findViewById<TextView>(R.id.text_change)
//        val btnRegister = findViewById<Button>(R.id.btn_register)
        btnRegister = findViewById(R.id.btn_register)

        btnRegister.setOnClickListener {
            textChange.text = getText(R.string.change_eui)
            val intent = Intent(this, SecondActivity::class.java)
            // key --> value
            intent.putExtra("EMAIL", "ahmdaliielsayed@gmail.com")
            intent.putExtra("MOBILE", "01063208399")
            startActivity(intent)
        }

        val btnViewGoogle = findViewById<Button>(R.id.btnViewGoogle)
        btnViewGoogle.setOnClickListener {
            val uri = Uri.parse("tel:01063208399")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        // will be visible --> start (thread - animation - sound)
        Log.d("MainActivity --> lifecycle: ", "onStart()")
    }

    override fun onResume() {
        super.onResume()

        // activity visible and user can interact
        Log.d("MainActivity --> lifecycle: ", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        // partially invisible
        Log.d("MainActivity --> lifecycle: ", "onPause()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // key --> value
        outState.putInt("COUNTER", counter)
    }

    override fun onStop() {
        super.onStop()
        // is NOT visible
        Log.d("MainActivity --> lifecycle: ", "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity --> lifecycle: ", "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity --> lifecycle: ", "onDestroy()")
    }
}