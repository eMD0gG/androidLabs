package com.example.androidlabs

data class StaticTaskItem(
    val distance: String,
    val time: String,
    val type: String,
    val timestamp: String,
    val user: String
) : java.io.Serializable