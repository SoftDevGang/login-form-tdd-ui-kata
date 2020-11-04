package org.devgang.loginform.viewimpl

import android.content.Context
import android.widget.LinearLayout
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.devgang.loginform.model.NewsItem
import org.devgang.loginform.model.NewsModel
import org.devgang.loginform.view.OverviewItemViewModel
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class NewsAdapterTest {

    private val newsAdapter: NewsAdapter = NewsAdapter()

    // --- test method of adapter not depending on UI code

    @Test
    fun should_count_1_for_empty_model() {
        val newsModel = createEmptyNewsModel()
        newsAdapter.setData(newsModel)

        val itemCount = newsAdapter.itemCount
        Assert.assertEquals(1, itemCount)
    }

    @Test
    fun should_count_items_in_model() {
        val newsModel = createFilledNewsModel()
        newsAdapter.setData(newsModel)

        val itemCount = newsAdapter.itemCount
        Assert.assertEquals(2, itemCount)
    }

    @Test
    fun should_return_view_type_no_news_for_empty_model() {
        val newsModel = createEmptyNewsModel()
        newsAdapter.setData(newsModel)

        val viewType = newsAdapter.getItemViewType(0)
        Assert.assertEquals(NewsAdapter.VIEW_TYPE_NO_NEWS, viewType)
    }

    @Test
    fun should_return_view_type_news_for_filled_model() {
        val newsModel = createFilledNewsModel()
        newsAdapter.setData(newsModel)

        val viewType = newsAdapter.getItemViewType(0)
        Assert.assertEquals(NewsAdapter.VIEW_TYPE_NEWS, viewType)
    }

    private fun createFilledNewsModel() = listOf(OverviewItemViewModel("news1"), OverviewItemViewModel("news2"))

    private fun createEmptyNewsModel() = emptyList<OverviewItemViewModel>()

    @Test
    @Ignore("Can't simulate Context, too complicated :-(")
    fun should_return_empty_view_holder_for_empty_model() {
        val newsModel = createEmptyNewsModel()
        newsAdapter.setData(newsModel)

        // val context = object :Context(){}
        val context = mock(Context::class.java)

        val viewHolder = newsAdapter.onCreateViewHolder(LinearLayout(context), NewsAdapter.VIEW_TYPE_NO_NEWS)
        Assert.assertTrue(viewHolder is NoNewsViewHolder)
    }
}
