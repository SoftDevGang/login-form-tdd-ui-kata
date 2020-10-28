package org.devgang.loginform

import org.devgang.loginform.model.NewsDownload
import org.devgang.loginform.view.OverviewItemViewModel
import org.devgang.loginform.view.OverviewUi

class NewsReaderOverviewPresenter(
    private var overviewUi: OverviewUi,
    private var newsDownload: NewsDownload
) {
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
