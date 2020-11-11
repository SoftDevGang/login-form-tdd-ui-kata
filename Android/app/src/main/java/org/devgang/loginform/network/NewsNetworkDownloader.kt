package org.devgang.loginform.network

import io.reactivex.Observable
import org.devgang.loginform.model.NewsDownload
import org.devgang.loginform.model.NewsModel

class NewsNetworkDownloader(url: String) : NewsDownload {
    override fun downloadNews(): Observable<NewsModel> {
        TODO("Not yet implemented")
    }

}
