package com.example.androidlabs

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TaskItem::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getTaskItemDao() : TaskItemDao
}