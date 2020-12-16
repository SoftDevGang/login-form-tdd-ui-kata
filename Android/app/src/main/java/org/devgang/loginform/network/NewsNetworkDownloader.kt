package org.devgang.loginform.network

import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import org.devgang.loginform.model.NewsItem
import org.devgang.loginform.model.NewsModel
import java.io.IOException
import java.net.HttpURLConnection

class NewsNetworkDownloader(private val url: String) : NewsDownload {
    override fun newsUpdates(): Observable<NewsModel> {
        return Observable.fromCallable { downloadNews() }
    }

    private fun downloadNews(): NewsModel {
        val body = download()
        return parse(body)
    }

    private fun download(): String? {
        val client = OkHttpClient()
        val request =
            Request.Builder().url(url).addHeader("Content-Type", "application/json").build()
        val response = client.newCall(request).execute()

        if (response.code != HttpURLConnection.HTTP_OK) {
            throw IOException("Network Error ${response.code}")
        }
        return response.body?.string()
    }

    private fun parse(body: String?): NewsModel {
        val gson = Gson()
        val newsItems: Array<NewsItem> = gson.fromJson(body, Array<NewsItem>::class.java)
        return NewsModel(newsItems)
    }

}
