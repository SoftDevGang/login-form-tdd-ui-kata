package org.devgang.loginform

class NewsDownloaderStub(var newsModel: NewsModel) : NewsDownload {

    override fun downloadNews(): NewsModel {
        return newsModel
    }
}
