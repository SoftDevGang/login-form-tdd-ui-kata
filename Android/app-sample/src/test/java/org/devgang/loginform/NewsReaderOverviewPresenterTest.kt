package org.devgang.loginform

import org.junit.Test

import org.junit.Assert.*

class NewsReaderOverviewPresenterTest {
    @Test
    fun should_set_no_results_for_no_news_items() {
        val overviewUi = OverviewUiMock()
        val presenter = NewsReaderOverviewPresenter(overviewUi)
        presenter.createDummyData(true)

        assertTrue(overviewUi.overviewItemViewModel == null)
    }

    @Test
    fun should_display_single_news_item() {
        val overviewUi = OverviewUiMock()
        val presenter = NewsReaderOverviewPresenter(overviewUi)
        presenter.createDummyData(false)
        assertEquals(overviewUi.overviewItemViewModel!!.newsModel.items, 2)
    }
}
