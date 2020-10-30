package org.devgang.loginform.model

class NewsDownloaderStub(private var newsModel: NewsModel) : NewsDownload {

    override fun downloadNews(): NewsModel {
        return newsModel
    }
}
