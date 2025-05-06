package com.example.androidlabs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch
import java.sql.Date

class MyViewModel : ViewModel() {
    private val db = Depends.db

    val allTaskItems = db.getTaskItemDao().getAllTaskItems()

    fun addMyTaskItem(typeActivity: String) {
        viewModelScope.launch {
            val activity = TaskItem(
                user = "me",
                activityType =
                    when (typeActivity) {
                        "Велосипед" -> activType.BICYCLE
                        "Бег" -> activType.RUNNING
                        "Шаг" -> activType.WALKING
                        else -> activType.WALKING
                    },
                startTime = System.currentTimeMillis(),
                endTime = System.currentTimeMillis() + 300000
            )
            db.getTaskItemDao().add(activity)
        }
    }
}