package com.example.androidlabs

data class TaskItem(
    val distance: String,
    val time: String,
    val activityType: String,
    val timestamp: String,
    val user: String
) : java.io.Serializable