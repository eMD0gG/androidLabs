package com.example.androidlabs
import android.app.Activity
import android.os.Bundle

class MainActivity : Activity(){
    override fun onCreate(savedInstaceState: Bundle?) {
        super.onCreate(savedInstaceState)

        setContentView(R.layout.activity_hello_world)
    }
}
