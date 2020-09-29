package org.devgang.loginform

class NewsReaderOverviewPresenter(var overviewUi: OverviewUi, var newsModel: NewsModel) {
    fun onLoad() {
        if (newsModel.items.isEmpty()) {
            overviewUi.displayNoResultsFound()
        } else {
            val items = newsModel.items.map { OverviewItemViewModel(it.title) }
            overviewUi.setViewModel(items)
        }
    }

}
