package com.example.androidlabs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.androidlabs.databinding.ActivityBeforeTrackingBinding
import kotlinx.coroutines.launch

class BeforeTrackingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeforeTrackingBinding
    lateinit var myVM : MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeforeTrackingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataset = arrayOf("Велосипед", "Бег", "Шаг")
        val customAdapter = TRVAdapter(dataset)

        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = customAdapter
        (recyclerView.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false

        myVM = ViewModelProvider(this).get(MyViewModel :: class.java)
        binding.start.setOnClickListener{
            if(customAdapter.selectedItemPos == -1) return@setOnClickListener

            val typeActivity = dataset[customAdapter.selectedItemPos]

            lifecycleScope.launch {
                myVM.addMyTaskItem(typeActivity)
            }}
        binding.backBtn.setOnClickListener{
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
        }
    }
}