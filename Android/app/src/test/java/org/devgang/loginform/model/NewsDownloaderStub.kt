package org.devgang.loginform.model

import io.reactivex.Observable

class NewsDownloaderStub(private var newsModel: NewsModel) : NewsDownload {

    override fun downloadNews(): Observable<NewsModel> {
        return Observable.create { emitter ->
            emitter.onNext(newsModel)
            emitter.onComplete()
        }
    }
}
