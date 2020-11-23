package org.devgang.loginform

import io.reactivex.schedulers.Schedulers
import org.devgang.loginform.network.NewsDownloaderStub
import org.devgang.loginform.model.NewsItem
import org.devgang.loginform.model.NewsModel
import org.devgang.loginform.view.OverviewUiMock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NewsReaderOverviewPresenterTest {
    // --- test interaction from presenter -> view
    private val overviewUi = OverviewUiMock()

    @Test
    fun should_set_no_results_for_no_news_items() {
        val newsModel = NewsModel(arrayOf())
        val dummyNewsDownloader = NewsDownloaderStub(newsModel)
        val presenter = createPresenter(dummyNewsDownloader)
        presenter.onLoad()
        dummyNewsDownloader.sendNewsModel()
        assertTrue(overviewUi.noResultsFoundCalled)
    }

    @Test
    fun should_display_single_news_item() {
        val item = NewsItem("dummyNewsItemTitle")
        val newsModel = NewsModel(arrayOf(item))
        val dummyNewsDownloader = NewsDownloaderStub(newsModel)
        val presenter = createPresenter(dummyNewsDownloader)
        presenter.onLoad()
        dummyNewsDownloader.sendNewsModel()
        assertEquals(overviewUi.setItems.size, 1)
    }

    @Test
    fun should_not_process_data_after_dispose() {
        val item = NewsItem("dummyNewsItemTitle")
        val newsModel = NewsModel(arrayOf(item))
        val dummyNewsDownloader = NewsDownloaderStub(newsModel)
        val presenter = createPresenter(dummyNewsDownloader)
        presenter.onLoad()

        presenter.dispose()
        dummyNewsDownloader.sendNewsModel()

        assertEquals(overviewUi.setItems.size, 0)
    }

    private fun createPresenter(dummyNewsDownloader: NewsDownloaderStub): NewsReaderOverviewPresenter {
        return NewsReaderOverviewPresenter(
            overviewUi,
            dummyNewsDownloader,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    // TODO next test cases for presenter: click on displayed element comes back to presenter
}
