package org.devgang.loginform.model

import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class NewsDownloaderStub(private var newsModel: NewsModel) : NewsDownload {
    lateinit var observableEmitter: ObservableEmitter<NewsModel>

    override fun downloadNews(): Observable<NewsModel> {
        return Observable.create { emitter ->
            observableEmitter = emitter
        }
    }

    fun sendNewsModel() {
        observableEmitter.onNext(newsModel)
    }
}
