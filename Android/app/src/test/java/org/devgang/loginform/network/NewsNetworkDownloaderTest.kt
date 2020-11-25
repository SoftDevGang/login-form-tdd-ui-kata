package org.devgang.loginform.network

import io.reactivex.observers.TestObserver
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.devgang.loginform.model.NewsItem
import org.devgang.loginform.model.NewsModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

class NewsNetworkDownloaderTest {

    private val mockServer = MockWebServer()
    private val testObserver = TestObserver<NewsModel>()

    @Before
    fun startServer() {
        mockServer.start(8080)
    }

    @Test
    fun should_download_no_news() {
        val testUrl = prepareMockServer("[]")
        val newsNetworkDownloader = NewsNetworkDownloader(testUrl.toString())

        newsNetworkDownloader.newsUpdates().subscribe(testObserver)

        testObserver.awaitCount(1)
        testObserver.assertValue(NewsModel(emptyArray()))
    }

    @Test
    fun should_download_single_news() {
        val testUrl = prepareMockServer("[{\"title\":\"foo\"}]")
        val newsNetworkDownloader = NewsNetworkDownloader(testUrl.toString())

        newsNetworkDownloader.newsUpdates().subscribe(testObserver)

        testObserver.awaitCount(1)
        testObserver.assertValue(NewsModel(arrayOf(NewsItem("foo"))))
    }

    private fun prepareMockServer(body: String): HttpUrl {
        mockServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(body)
        )
        return mockServer.url("/results.json")
    }

    @After
    fun stopServer() {
        mockServer.shutdown()
        testObserver.dispose()
    }
}