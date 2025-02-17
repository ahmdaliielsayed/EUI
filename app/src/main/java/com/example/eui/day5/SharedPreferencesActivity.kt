package com.example.eui.day5

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eui.R

class SharedPreferencesActivity : AppCompatActivity() {

    private val selectCommand = "DELET * FROM colors WHERE name = red"

    private lateinit var sharedPref: SharedPreferences
    private lateinit var txtPreferences: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shared_preferences)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPref = getSharedPreferences("USER_PREFERENCES", MODE_PRIVATE)

        val username = sharedPref.getString("USERNAME", "UNKNOWN")
        val showTutorial = sharedPref.getBoolean("SHOW_TUTORIAL", true)

        txtPreferences = findViewById(R.id.txt_prefrences)
        txtPreferences.text = """
            username: $username
            showTutorial: $showTutorial
        """.trimIndent()
    }

    override fun onPause() {
        super.onPause()

        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString("USERNAME", "Ahmed")
        editor.putBoolean("SHOW_TUTORIAL", false)

        val commitResult: Boolean = editor.commit()

        if (commitResult) {
            Log.d("asd --> ", "onPause: SUCCESS")
        } else {
            Log.d("asd --> ", "onPause: FAILED")
        }
    }
}