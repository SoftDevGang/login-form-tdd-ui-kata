package org.devgang.loginform

class NewsReaderOverviewPresenter(var overviewUi: OverviewUi, var newsModel: NewsModel) {
    fun onLoad() {
        if(newsModel.items.isEmpty()) {
            overviewUi.displayNoResultsFound()
        }
    }

}
