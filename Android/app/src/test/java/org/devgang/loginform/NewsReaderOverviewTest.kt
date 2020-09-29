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
        val presenter = NewsReaderOverviewPresenter(overviewUi)
        presenter.onLoad()
        assertTrue(overviewUi.noResultsFoundCalled)
    }
}

class DummyOverviewUi : OverviewUi {
    var noResultsFoundCalled = false
    override fun displayNoResultsFound() {
        noResultsFoundCalled = true
    }
}
