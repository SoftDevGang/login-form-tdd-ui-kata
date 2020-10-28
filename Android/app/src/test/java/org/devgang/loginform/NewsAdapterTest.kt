package org.devgang.loginform

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsAdapterTest {

    private val newsAdapter: NewsAdapter = NewsAdapter()

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

    private fun createFilledNewsModel() = NewsModel(arrayOf(NewsItem("news1"), NewsItem("news2")))

    private fun createEmptyNewsModel() = NewsModel(arrayOf())


//    @Test
//    fun should_return_empty_view_holder_for_empty_model() {
//        val newsModel = NewsModel(arrayOf())
//        val newsAdapter = NewsAdapter()
//        newsAdapter.setData(newsModel)
//
//        val viewHolder = newsAdapter.onCreateViewHolder()
//        viewHolder.itemViewType
//        newsAdapter.bindViewHolder(viewHolder,0)
//        Assert.assertEquals(0, itemCount)
//    }
}
