package org.devgang.loginform.network

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import org.devgang.loginform.model.NewsDownload
import org.devgang.loginform.model.NewsModel
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class NewsNetworkDownloader(val url: String) : NewsDownload {
    override fun downloadNews(): Observable<NewsModel> {
        return Observable.create {


            val client = OkHttpClient()
            val request = Request.Builder().url(url).addHeader("Content-Type", "application/json").build();

            val response = client.newCall(request).execute()

            val body = response.body?.string()
            print(body)
            it.onNext(NewsModel(emptyArray()))
            it.onComplete()
        }
    }

}
