package com.example.androidlabs

import android.content.Context
import androidx.room.Room

object Depends {
    lateinit var context: Context
    lateinit var db: MyDatabase

    fun initDatabase(){
        db = Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "myDB"
        ).fallbackToDestructiveMigration().build()
    }
}