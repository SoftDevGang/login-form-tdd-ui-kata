package org.devgang.loginform

import org.junit.Test

import org.junit.Assert.*

class NewsReaderOverviewPresenterTest {
    @Test
    fun should_set_no_results_for_no_news_items() {
        val overviewUi = DummyOverviewUi()
        val newsModel = NewsModel(arrayOf())
        val dummyNewsDownloader = NewsDownloaderStub(newsModel)
        val presenter = NewsReaderOverviewPresenter(overviewUi, dummyNewsDownloader)
        presenter.onLoad()
        assertTrue(overviewUi.noResultsFoundCalled)
    }

    @Test
    fun should_display_single_news_item() {
        val overviewUi = DummyOverviewUi()
        val item = NewsItem("dummyNewsItemTitle")
        val newsModel = NewsModel(arrayOf(item))
        val dummyNewsDownloader = NewsDownloaderStub(newsModel)
        val presenter = NewsReaderOverviewPresenter(overviewUi, dummyNewsDownloader)
        presenter.onLoad()
        assertEquals(overviewUi.setItems.size, 1)
    }
}

class NewsDownloaderStub(var newsModel: NewsModel) : NewsDownload {

    override fun downloadNews(): NewsModel {
        return newsModel
    }
}

class DummyOverviewUi : OverviewUi {
    var noResultsFoundCalled = false
    var setItems: List<OverviewItemViewModel> = emptyList()
    override fun displayNoResultsFound() {
        noResultsFoundCalled = true
    }

    override fun setViewModel(items: List<OverviewItemViewModel>) {
        setItems = items

    }
}
