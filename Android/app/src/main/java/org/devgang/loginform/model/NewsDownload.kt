package org.devgang.loginform.model

import io.reactivex.Observable

interface NewsDownload {
    fun downloadNews(): Observable<NewsModel>
}
