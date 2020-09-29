package org.devgang.loginform

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NewsReaderOverviewTest {
    @Test
    fun should_set_no_results_for_no_news_items() {
        val overviewUi = DummyOverviewUi()
        val newsModel = NewsModel(arrayOf())
        val presenter = NewsReaderOverviewPresenter(overviewUi, newsModel)
        presenter.onLoad()
        assertTrue(overviewUi.noResultsFoundCalled)
    }

    @Test
    fun should_display_single_news_item() {
        val overviewUi = DummyOverviewUi()
        val item = NewsItem("dummyNewsItemTitle")
        val newsModel = NewsModel(arrayOf(item))
        val presenter = NewsReaderOverviewPresenter(overviewUi, newsModel)
        presenter.onLoad()
        assertEquals(overviewUi.setItems.size, 1)
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
