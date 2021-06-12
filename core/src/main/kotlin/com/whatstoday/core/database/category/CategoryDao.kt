package com.whatstoday.core.database.category

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.whatstoday.core.database.TaskCategory

@Dao
interface CategoryDao {


    @Transaction
    @Query("SELECT * FROM category")
    fun getAllCategories(): LiveData<List<TaskCategory>>

    @Transaction
    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryByTask(id: String): LiveData<TaskCategory>
}
