package org.devgang.loginform

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsAdapterTest {

    @Test
    fun should_count_0_for_empty_model() {
        val newsModel = NewsModel(arrayOf())
        val newsAdapter = NewsAdapter()
        newsAdapter.setData(newsModel)

        val itemCount = newsAdapter.itemCount
        Assert.assertEquals(0, itemCount)
    }
}

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var newsItem = emptyArray<NewsItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return newsItem.size
    }

    fun setData(newsModel: NewsModel) {
        newsItem = newsModel.items
    }

}