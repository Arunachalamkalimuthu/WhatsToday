 

package com.whatstoday.core.di

import com.whatstoday.core.BuildConfig
import com.whatstoday.core.di.modules.NetworkModule
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class NetworkModuleTest {

    private lateinit var networkModule: NetworkModule

    @Before
    fun setUp() {
        networkModule = NetworkModule()
    }

    @Test
    fun verifyProvidedHttpLoggingInterceptor() {
        val interceptor = networkModule.provideHttpLoggingInterceptor()
        assertEquals(HttpLoggingInterceptor.Level.BODY, interceptor.level)
    }

    @Test
    fun verifyProvidedHttpClient() {
        val interceptor = mockk<HttpLoggingInterceptor>()
        val httpClient = networkModule.provideHttpClient(interceptor)

        assertEquals(1, httpClient.interceptors.size)
        assertEquals(interceptor, httpClient.interceptors.first())
    }

    @Test
    fun verifyProvidedRetrofitBuilder() {
        val retrofit = networkModule.provideRetrofitBuilder()

        assertEquals(BuildConfig.MARVEL_API_BASE_URL, retrofit.baseUrl().toUrl().toString())
    }

    @Test
    fun verifyProvidedMarvelService() {
        val retrofit = mockk<Retrofit>()
        val marvelService = mockk<MarvelService>()
        val serviceClassCaptor = slot<Class<*>>()

        every { retrofit.create<MarvelService>(any()) } returns marvelService

        networkModule.provideMarvelService(retrofit)

        verify { retrofit.create(capture(serviceClassCaptor)) }
        assertEquals(MarvelService::class.java, serviceClassCaptor.captured)
    }

    @Test
    fun verifyProvidedMarvelRepository() {
        val marvelService = mockk<MarvelService>()
        val TaskRepository = networkModule.provideMarvelRepository(marvelService)

        assertEquals(marvelService, TaskRepository.service)
    }
}
