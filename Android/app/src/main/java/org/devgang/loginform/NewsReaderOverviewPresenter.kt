package org.devgang.loginform

class NewsReaderOverviewPresenter(private var overviewUi: OverviewUi, private var newsDownload: NewsDownload) {
    fun onLoad() {
        val newsModel = newsDownload.downloadNews()
        if (newsModel.items.isEmpty()) {
            overviewUi.displayNoResultsFound()
        } else {
            val items = newsModel.items.map { OverviewItemViewModel(it.title) }
            overviewUi.setViewModel(items)
        }
    }
}
