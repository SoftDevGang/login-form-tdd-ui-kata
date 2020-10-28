package org.devgang.loginform.model

class NewsDownloaderStub(var newsModel: NewsModel) : NewsDownload {

    override fun downloadNews(): NewsModel {
        return newsModel
    }
}
