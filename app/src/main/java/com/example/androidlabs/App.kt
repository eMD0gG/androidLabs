package com.example.androidlabs

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Depends.context = applicationContext
        Depends.initDatabase()
    }
}