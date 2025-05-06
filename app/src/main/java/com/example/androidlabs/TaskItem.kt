package com.example.androidlabs

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class activType(val displayName: String){
    RUNNING("Бег"),
    BICYCLE("Велосипед"),
    WALKING("Шаг")
}

@Entity
data class TaskItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val user: String,
    val activityType: activType,
    val startTime: Long = System.currentTimeMillis(),
    val endTime: Long = System.currentTimeMillis() + 300000
) : java.io.Serializable