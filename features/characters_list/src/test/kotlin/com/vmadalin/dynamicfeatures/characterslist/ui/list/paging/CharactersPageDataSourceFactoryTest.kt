

package com.whatstoday.dynamicfeatures.characterslist.ui.list.paging

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
    lateinit var dataSourceFactory: CharactersPageDataSourceFactory
    @MockK(relaxed = true)
    lateinit var providerDataSource: Provider<CharactersPageDataSource>
    @SpyK
    var sourceLiveData = MutableLiveData<CharactersPageDataSource>()

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
        } returns CharactersPageDataSource(mockk(), mockk(), mockk())

        val dataSource = dataSourceFactory.create() as CharactersPageDataSource

        verify { dataSourceFactory.sourceLiveData.postValue(dataSource) }
    }

    @Test
    fun refreshDataSource_ShouldInvalidateData() {
        val dataSource = mockk<CharactersPageDataSource>(relaxed = true)
        every { sourceLiveData.value } returns dataSource

        dataSourceFactory.refresh()

        verify { dataSource.invalidate() }
        verify(exactly = 0) { dataSource.retry() }
    }

    @Test
    fun retryDataSource_ShouldRetryData() {
        val dataSource = mockk<CharactersPageDataSource>(relaxed = true)
        every { sourceLiveData.value } returns dataSource

        dataSourceFactory.retry()

        verify { dataSource.retry() }
        verify(exactly = 0) { dataSource.invalidate() }
    }
}
