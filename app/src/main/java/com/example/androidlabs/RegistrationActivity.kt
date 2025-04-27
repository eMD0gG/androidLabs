package com.example.androidlabs
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidlabs.databinding.ActivityMainBinding
import com.example.androidlabs.databinding.ActivityRegistrationBinding
import com.google.android.material.appbar.MaterialToolbar

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navBar = binding.topAppBar

        navBar.setNavigationOnClickListener {
            finish()
        }


        val text = binding.registrationInfo.text.toString()
        val spannable = SpannableString(text)
        val clickable = Clickable()
        spannable.setSpan(clickable, 47, 75, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val clickable2 = Clickable()
        spannable.setSpan(clickable2, 128, 155, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.registrationInfo.text = spannable
    }
}