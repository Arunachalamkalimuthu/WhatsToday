package com.whatstoday.core.database.task

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import com.whatstoday.core.database.TaskCategory
import com.whatstoday.core.database.characterfavorite.CharacterFavoriteDao
import javax.inject.Inject

class TaskRepository @Inject constructor(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal val taskDao: TaskDao
) {

    suspend fun getTodayTask(): LiveData<List<Task>> =
        taskDao.getTodayTasks()

}
