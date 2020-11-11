package org.devgang.loginform.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class NewsNetworkDownloaderTest {


    @Test
    fun should_download_no_news() {
        val mockServer = MockWebServer()
        mockServer.start(8080)
        mockServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody("[]"))
        val testUrl = mockServer.url("/results.json")

        val newsNetworkDownloader = NewsNetworkDownloader(testUrl.toString())


        //TODO
        // Downloader starten (HOST/PORT), Scheduler übergeben
        // Warten auf Response
        // Observe if empty
        // Close Observer & Close OK-HTTP-SERVER
        // https://stackoverflow.com/questions/26699147/how-to-use-testscheduler-in-rxjava

       Thread.sleep(1000 * 60 * 60)
        mockServer.shutdown()
        assert(false)
    }
}