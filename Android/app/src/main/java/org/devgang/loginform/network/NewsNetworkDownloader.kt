package org.devgang.loginform.network

import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import org.devgang.loginform.model.NewsDownload
import org.devgang.loginform.model.NewsItem
import org.devgang.loginform.model.NewsModel


class NewsNetworkDownloader(val url: String) : NewsDownload {
    override fun downloadNews(): Observable<NewsModel> {
        return Observable.create {

            val client = OkHttpClient()
            val request = Request.Builder().url(url).addHeader("Content-Type", "application/json").build();

            val response = client.newCall(request).execute()

            val body = response.body?.string()

            val gson = Gson()

            val entity: Array<NewsItem> = gson.fromJson(body, Array<NewsItem>::class.java)

            it.onNext(NewsModel(entity))
            it.onComplete()
        }
    }

}
