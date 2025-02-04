package com.example.eui.day2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eui.R

class RecyclerViewActivity : AppCompatActivity() {

//    private val dataSource = IntRange(0, 100).toList()
    private val dataSource: List<Day> = listOf(
        Day("Saturday", R.drawable.one),
        Day("Sunday", R.drawable.two),
        Day("Monday", R.drawable.three),
        Day("Tuesday", R.drawable.four),
        Day("Wednesday", R.drawable.five),
        Day("Thursday", R.drawable.six),
        Day("Friday", R.drawable.seven),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_recycler_view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recycler: RecyclerView = findViewById(R.id.recycler_view)
        val myAdapter = MyAdapter()
        recycler.adapter = myAdapter
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)




        myAdapter.submitList(dataSource)
    }
}