package org.devgang.loginform

import org.devgang.loginform.model.NewsDownloaderStub
import org.devgang.loginform.model.NewsItem
import org.devgang.loginform.model.NewsModel
import org.devgang.loginform.view.OverviewUiMock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NewsReaderOverviewPresenterTest {

    // --- test interaction from presenter -> view

    @Test
    fun should_set_no_results_for_no_news_items() {
        val overviewUi = OverviewUiMock()
        val newsModel = NewsModel(arrayOf())
        val dummyNewsDownloader = NewsDownloaderStub(newsModel)
        val presenter = NewsReaderOverviewPresenter(overviewUi, dummyNewsDownloader)
        presenter.onLoad()
        assertTrue(overviewUi.noResultsFoundCalled)
    }

    @Test
    fun should_display_single_news_item() {
        val overviewUi = OverviewUiMock()
        val item = NewsItem("dummyNewsItemTitle")
        val newsModel = NewsModel(arrayOf(item))
        val dummyNewsDownloader = NewsDownloaderStub(newsModel)
        val presenter = NewsReaderOverviewPresenter(overviewUi, dummyNewsDownloader)
        presenter.onLoad()
        assertEquals(overviewUi.setItems.size, 1)
    }

    // TODO next test cases for presenter: click on displayed element comes back to presenter
}
