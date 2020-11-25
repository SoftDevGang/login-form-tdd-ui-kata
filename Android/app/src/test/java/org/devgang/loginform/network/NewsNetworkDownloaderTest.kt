package org.devgang.loginform.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.devgang.loginform.model.NewsItem
import org.devgang.loginform.model.NewsModel
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class) // TODO remove @RunWith, this is a plain unit test
class NewsNetworkDownloaderTest {

    // TODO move mock server in before and after of test

    @Test
    fun should_download_no_news() {
        val mockServer = MockWebServer()
        mockServer.start(8080)
        mockServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody("[]"))
        val testUrl = mockServer.url("/results.json")

        val newsNetworkDownloader = NewsNetworkDownloader(testUrl.toString())

        val testObserver = TestObserver<NewsModel>()
        newsNetworkDownloader.newsUpdates().subscribe(testObserver)

        testObserver.awaitCount(1)
        testObserver.assertValue(NewsModel(emptyArray()))

        testObserver.dispose()
        mockServer.shutdown()
    }

    @Test
    fun should_download_single_news() {
        val mockServer = MockWebServer()
        mockServer.start(8080)
        mockServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("[{\"title\":\"foo\"}]")
        )
        val testUrl = mockServer.url("/results.json")

        val newsNetworkDownloader = NewsNetworkDownloader(testUrl.toString())

        val testObserver = TestObserver<NewsModel>()
        newsNetworkDownloader.newsUpdates().subscribe(testObserver)

        testObserver.awaitCount(1)
        testObserver.assertValue(NewsModel(arrayOf(NewsItem("foo"))))

        testObserver.dispose()
        mockServer.shutdown()
    }
}