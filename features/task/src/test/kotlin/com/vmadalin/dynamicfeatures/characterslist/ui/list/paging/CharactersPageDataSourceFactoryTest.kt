

package com.whatstoday.dynamicfeatures.task.ui.list.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import io.mockk.verify
import javax.inject.Provider
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersPageDataSourceFactoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMockKs(overrideValues = true)
    lateinit var dataSourceFactory: TaskListPageDataSourceFactory
    @MockK(relaxed = true)
    lateinit var providerDataSource: Provider<TaskListPageDataSource>
    @SpyK
    var sourceLiveData = MutableLiveData<TaskListPageDataSource>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeFactory_WithoutCreate_ShouldNotHaveDataSource() {
        verify(exactly = 0) { dataSourceFactory.sourceLiveData.value }

        assertNull(dataSourceFactory.sourceLiveData.value)
    }

    @Test
    fun initializeFactory_WithCreate_ShouldHaveDataSource() {
        every {
            providerDataSource.get()
        } returns TaskListPageDataSource(mockk(), mockk(), mockk())

        val dataSource = dataSourceFactory.create() as TaskListPageDataSource

        verify { dataSourceFactory.sourceLiveData.postValue(dataSource) }
    }

    @Test
    fun refreshDataSource_ShouldInvalidateData() {
        val dataSource = mockk<TaskListPageDataSource>(relaxed = true)
        every { sourceLiveData.value } returns dataSource

        dataSourceFactory.refresh()

        verify { dataSource.invalidate() }
        verify(exactly = 0) { dataSource.retry() }
    }

    @Test
    fun retryDataSource_ShouldRetryData() {
        val dataSource = mockk<TaskListPageDataSource>(relaxed = true)
        every { sourceLiveData.value } returns dataSource

        dataSourceFactory.retry()

        verify { dataSource.retry() }
        verify(exactly = 0) { dataSource.invalidate() }
    }
}
