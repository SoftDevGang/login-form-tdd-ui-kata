package org.devgang.loginform

class NewsReaderOverviewPresenter(var overviewUi: OverviewUi) {
    fun onLoad() {
        overviewUi.displayNoResultsFound()
    }

}
