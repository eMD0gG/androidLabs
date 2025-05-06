package com.example.androidlabs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TaskItemDao {
    @Insert
    suspend fun add(taskitem: TaskItem)

    @Delete
    suspend fun delete(taskitem:  TaskItem)

    @Update
    suspend fun update(taskitem:  TaskItem)

    @Query("SELECT * FROM TaskItem")
    fun getAllTaskItems(): androidx.lifecycle.LiveData<List<TaskItem>>
}