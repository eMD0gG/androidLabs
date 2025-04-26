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
import com.google.android.material.appbar.MaterialToolbar

class RegistrationActivity : AppCompatActivity() {

    private lateinit var registrationInfo : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navBar = findViewById<MaterialToolbar>(R.id.topAppBar)

        navBar.setNavigationOnClickListener {
            finish()
        }

        registrationInfo = findViewById<TextView>(R.id.registration_info)

        val text = registrationInfo.text.toString()
        val spannable = SpannableString(text)
        val clickable = Clickable()
        spannable.setSpan(clickable, 47, 75, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val clickable2 = Clickable()
        spannable.setSpan(clickable2, 128, 155, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        registrationInfo.text = spannable
    }
}