package org.devgang.loginform.network

import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import org.devgang.loginform.model.NewsItem
import org.devgang.loginform.model.NewsModel

class NewsNetworkDownloader(private val url: String) : NewsDownload {
    override fun downloadNews(): Observable<NewsModel> {
        return Observable.create {

            val newsModel = downloadNewsBlocking()

            it.onNext(newsModel)
            it.onComplete()
        }
    }

    private fun downloadNewsBlocking(): NewsModel {
        // TODO structure: split http and parsing logic, at least methods
        val client = OkHttpClient()
        val request = Request.Builder().url(url).addHeader("Content-Type", "application/json").build()
        val response = client.newCall(request).execute()
        val body = response.body?.string()

        val gson = Gson()
        val newsItems: Array<NewsItem> = gson.fromJson(body, Array<NewsItem>::class.java)
        return NewsModel(newsItems)
    }

}
