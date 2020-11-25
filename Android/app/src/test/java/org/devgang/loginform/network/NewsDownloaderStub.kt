package org.devgang.loginform.network

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import org.devgang.loginform.model.NewsModel

class NewsDownloaderStub(private var newsModel: NewsModel) : NewsDownload {
    private lateinit var observableEmitter: ObservableEmitter<NewsModel>

    override fun downloadNews(): Observable<NewsModel> {
        return Observable.create { emitter ->
            observableEmitter = emitter
        }
    }

    fun sendNewsModel() {
        observableEmitter.onNext(newsModel)
    }
}
