package com.example.androidlabs

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.androidlabs.databinding.ActivityBaseBinding
import com.example.androidlabs.databinding.ActivityRegistrationBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding
    lateinit var myVM : MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myVM = ViewModelProvider(this).get(MyViewModel::class.java)
        val fragmentActivity = ActivityFragment.newInstance("", "")
        val fragmentProfile = ProfileFragment.newInstance("", "")

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainerView, fragmentActivity, "activity")
            add(R.id.fragmentContainerView, fragmentProfile, "profile")
            hide(fragmentProfile)
            commit()
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_1 -> {
                    supportFragmentManager.beginTransaction().apply {
                        show(fragmentActivity)
                        hide(fragmentProfile)
                        commit()
                    }
                    true
                }

                R.id.item_2 -> {
                    supportFragmentManager.beginTransaction().apply {
                        show(fragmentProfile)
                        hide(fragmentActivity)
                        commit()
                    }
                    true
                }
                else -> false
            }
        }
    }
}