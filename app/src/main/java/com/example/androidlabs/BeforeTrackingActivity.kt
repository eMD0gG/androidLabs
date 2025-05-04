package com.example.androidlabs

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator

class BeforeTrackingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_before_tracking)

        val dataset = arrayOf("Велосипед", "Бег", "Шаг")
        val customAdapter = TRVAdapter(dataset)

        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = customAdapter
        (recyclerView.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false


        findViewById<Button>(R.id.start).setOnClickListener{
//            val typeActivity =
        }
    }
}