package com.whatstoday.core.database.task

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface TaskDao {


    @Query("SELECT * FROM task where expectedDate = date('now')")
    suspend fun getTodayTasks(): LiveData<List<Task>>

}
