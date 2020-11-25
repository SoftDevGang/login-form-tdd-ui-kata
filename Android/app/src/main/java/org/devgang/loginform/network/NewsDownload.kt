package org.devgang.loginform.network

import io.reactivex.Observable
import org.devgang.loginform.model.NewsModel

interface NewsDownload {
    // TODO separate RX from logic: make this a simple function again
    // and create a "toObservable()" function which reports the observable from the download
    // see https://github.com/ReactiveX/RxKotlin
    // Fix all subclasses (production and test)
    fun newsUpdates(): Observable<NewsModel>
}
