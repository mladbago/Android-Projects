package com.example.payapp.data

import com.example.payapp.APIInterface
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ImageApiServiceTest {
    private lateinit var service: APIInterface
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))//We will use MockWebServers url
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)
    }

    private fun enqueueMockResponse(
        fileName: String
    ) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            server.enqueue(mockResponse)
        }
    }

    @Test
    fun getSearchedResult_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("PayData.json")
            val responseBody = service.getData()
            val request = server.takeRequest()
            assertThat(request.path).isEqualTo("/pays")
        }
    }


    @After
    fun tearDown() {
        server.shutdown()
    }
}
