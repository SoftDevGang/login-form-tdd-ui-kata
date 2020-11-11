package org.devgang.loginform.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.apache.tools.ant.taskdefs.Sleep
import org.devgang.loginform.network.NewsNetworkDownloader
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class NewsNetworkDownloaderTest {


    @Test
    fun should_download_no_news() {
        val mockServer = MockWebServer()
        val mockResponse = MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody("[]")
        // mockServer.url("/results.json")
        mockServer.enqueue(mockResponse)
        mockServer.start(8080)

        //val newsNetworkDownloader = NewsNetworkDownloader("http://127.0.0.1:8080/", )


        //TODO
        // Downloader starten (HOST/PORT), Scheduler übergeben
        // Warten auf Response
        // Observe if empty
        // Close Observer & Close OK-HTTP-SERVER
        // https://stackoverflow.com/questions/26699147/how-to-use-testscheduler-in-rxjava

        mockServer.shutdown()
        assert(false)
    }
}