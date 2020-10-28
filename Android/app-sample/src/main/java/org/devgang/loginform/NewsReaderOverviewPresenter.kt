package org.devgang.loginform

class NewsReaderOverviewPresenter(var overviewUi: OverviewUi) {

    private val NO_NEWS = 1
    private val NEWS = 2

    fun createDummyData(createEmptyList: Boolean) {
        if (createEmptyList) {
            val item = NewsItem("NO NEWS")
            val newsModel = NewsModel()
            newsModel.items = listOf(item)
            val overviewItemViewModel = OverviewItemViewModel()
            overviewItemViewModel.modelType = NO_NEWS
            overviewItemViewModel.newsModel = newsModel
            overviewUi.setViewModel(overviewItemViewModel)
        } else {
            val item = NewsItem("dummyNewsItemTitle1")
            val item2 = NewsItem("dummyNewsItemTitle2")
            val newsModel = NewsModel()
            newsModel.items = listOf(item, item2)
            val overviewItemViewModel = OverviewItemViewModel()
            overviewItemViewModel.modelType = NEWS
            overviewItemViewModel.newsModel = newsModel
            overviewUi.setViewModel(overviewItemViewModel)
        }
    }

}
