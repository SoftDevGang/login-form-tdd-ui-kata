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

@RunWith(AndroidJUnit4::class)
class NewsNetworkDownloaderTest {

//TODO: move server in before and after

    @Test
    fun should_download_no_news() {
        val mockServer = MockWebServer()
        mockServer.start(8080)
        mockServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody("[]"))
        val testUrl = mockServer.url("/results.json")

        val newsNetworkDownloader = NewsNetworkDownloader(testUrl.toString())

        val testObserver = TestObserver<NewsModel>()
        newsNetworkDownloader.downloadNews().subscribe(testObserver)

        testObserver.awaitCount(1)
        testObserver.assertValue(NewsModel(emptyArray()))

        testObserver.dispose()
        mockServer.shutdown()
    }
}