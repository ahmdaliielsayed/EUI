package com.example.eui.day3

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.eui.R

class Day3Activity : AppCompatActivity(), Communicator {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var dynamicFragment: DynamicFragment
    private lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_day3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fragmentManager = supportFragmentManager
        if (savedInstanceState == null) {
            dynamicFragment = DynamicFragment()

            fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.add(R.id.fragmentContainerViewDynamic, dynamicFragment, "dynamic_fragment")
            fragmentTransaction.commit()
            Log.d("asd --> ", "First creation")
        } else {
            dynamicFragment = fragmentManager.findFragmentByTag("dynamic_fragment") as DynamicFragment
            Log.d("asd --> ", "reuse")
        }
    }

    override fun sendMessage(msg: String) {
        dynamicFragment.receiveMessage(msg)
    }
}