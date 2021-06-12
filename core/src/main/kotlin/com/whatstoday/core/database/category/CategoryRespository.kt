package com.whatstoday.core.database.category

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import com.whatstoday.core.database.TaskCategory
import javax.inject.Inject


class CategoryRespository @Inject constructor(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal val categoryDao: CategoryDao
) {

    fun getAllCategories(): LiveData<List<TaskCategory>> =
        categoryDao.getAllCategories()


}

